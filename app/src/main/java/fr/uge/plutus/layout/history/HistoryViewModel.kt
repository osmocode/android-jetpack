package fr.uge.plutus.layout.history

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import fr.uge.plutus.data.repository.TransactionRepository

class HistoryViewModel(context: Context) : ViewModel() {

    private val transactionRepository = TransactionRepository.build(context)

    init {
        /*
        viewModelScope.launch {
            transactionRepository.createTransaction(Transaction(Math.random().toInt(), "Miam", Price("Euros", 26.0)))
            transactionRepository.createTransaction(
                Transaction(
                    Math.random().toInt(),
                    "Essence",
                    Price("Euros", 112.0)
                )
            )
            transactionRepository.createTransaction(
                Transaction(
                    Math.random().toInt(),
                    "zeklfjknezkfje",
                    Price("Euros", 216.0)
                )
            )
        }
         */
    }

}

class HistoryViewModelFactory(
    private val context: Context
): ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(HistoryViewModel::class.java)) {
            return HistoryViewModel(context) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}