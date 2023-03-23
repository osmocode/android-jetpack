package fr.uge.plutus.broadcast

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast
import fr.uge.plutus.notification.TransactionNotification
import javax.inject.Inject

class TransactionBroadcast: BroadcastReceiver() {

    @Inject
    lateinit var transactionNotification: TransactionNotification

    override fun onReceive(context: Context?, intent: Intent?) {
        try {
            transactionNotification.pushNotification()
        } catch (e: Exception) {
            Toast.makeText(context, "Something get wrong.", Toast.LENGTH_SHORT).show()
        }
    }

}