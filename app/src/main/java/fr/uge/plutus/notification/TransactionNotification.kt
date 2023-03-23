package fr.uge.plutus.notification

import android.app.AlarmManager
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import fr.uge.plutus.MainApplication
import fr.uge.plutus.R
import fr.uge.plutus.broadcast.TransactionBroadcast
import fr.uge.plutus.data.model.Transaction
import kotlin.random.Random

class TransactionNotification(
    private val context: Context
) {
    private val manager = NotificationManagerCompat.from(context)
    private val alarm = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager

    companion object {
        const val TRANSACTION_CHANNEL_ID = "TRANSACTION_CHANNEL"
    }

    init {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            val channel = NotificationChannel(
                TRANSACTION_CHANNEL_ID,
                "Plutus",
                NotificationManager.IMPORTANCE_DEFAULT
            )
            this.manager.createNotificationChannel(channel)
        }
    }

    fun pushAlarm(transaction: Transaction) {
        val intent = Intent(context, TransactionBroadcast::class.java)
        val pending = PendingIntent.getActivity(context, 1, intent,
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) PendingIntent.FLAG_IMMUTABLE else 0)
        alarm.set(AlarmManager.RTC_WAKEUP, transaction.timestamp, pending)
    }

    fun pushNotification() {
        val intent = Intent(context, MainApplication::class.java)
        val pending = PendingIntent.getActivity(context, 1,intent,
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) PendingIntent.FLAG_IMMUTABLE else 0)
        val notification = NotificationCompat.Builder(context, TRANSACTION_CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setContentTitle("Transaction created")
            .setContentIntent(pending)
            .build()
        manager.notify(Random(System.currentTimeMillis()).nextInt(), notification)
    }

}