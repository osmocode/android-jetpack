package fr.uge.plutus.layout.history

import android.app.Application
import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import fr.uge.plutus.data.model.Price
import fr.uge.plutus.data.model.Transaction
import fr.uge.plutus.data.repository.TransactionRepository
import kotlinx.coroutines.launch

class HistoryViewModel(context: Context) : ViewModel() {

    private val transactionRepository = TransactionRepository.build(context)

    init {
        viewModelScope.launch {
            transactionRepository.createTransaction(Transaction(1, "Miam", Price("Euros", 26.0)))
            transactionRepository.createTransaction(
                Transaction(
                    2,
                    "Essence",
                    Price("Euros", 112.0)
                )
            )
            transactionRepository.createTransaction(
                Transaction(
                    3,
                    "zeklfjknezkfje",
                    Price("Euros", 216.0)
                )
            )
        }
    }

//    companion object {
////        val viewModel = viewModel<HistoryViewModel>(factory = object : ViewModelProvider.Factory {
////            override fun <T : ViewModel> create(modelClass: Class<T>): T {
////                @Suppress("UNCHECKED_CAST")
////                return HistoryViewModel(application) as T
////            }
////        })
//
//        fun <T : ViewModel> factory(context: Context): ViewModelProvider.Factory {
//            return object : ViewModelProvider.Factory {
//                override fun <T : ViewModel> create(modelClass: Class<T>): T {
//                    @Suppress("UNCHECKED_CAST")
//                    return HistoryViewModel(context) as T
//                }
//            }
//        }
//    }
}