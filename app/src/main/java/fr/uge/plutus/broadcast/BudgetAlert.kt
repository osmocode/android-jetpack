package fr.uge.plutus.broadcast

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast

class BudgetAlert: BroadcastReceiver() {

    override fun onReceive(context: Context?, intent: Intent?) {
        try {

        } catch (e: Exception) {
            Toast.makeText(context, "Something get wrong.", Toast.LENGTH_SHORT).show()
        }
    }

}