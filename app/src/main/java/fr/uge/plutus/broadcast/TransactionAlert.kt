package fr.uge.plutus.broadcast

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast
import androidx.core.app.NotificationCompat
import javax.inject.Inject

class TransactionAlert: BroadcastReceiver() {

    @Inject
    lateinit var notificationBuilder: NotificationCompat.Builder

    override fun onReceive(context: Context?, intent: Intent?) {
        try {
            notificationBuilder.setContentText("Transaction coming soon")
            notificationBuilder.build()
        } catch (e: Exception) {
            Toast.makeText(context, "Something get wrong.", Toast.LENGTH_SHORT).show()
        }
    }

}