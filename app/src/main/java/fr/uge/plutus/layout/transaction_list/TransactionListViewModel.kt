package fr.uge.plutus.layout.transaction_list

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import fr.uge.plutus.data.repository.TransactionRepository
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TransactionListViewModel @Inject constructor(
    private val transactionRepository: TransactionRepository
) : ViewModel() {

    private val _state = mutableStateOf(TransactionListState())
    val state: State<TransactionListState> = _state

    private var getTransactionsJob: Job? = null
    private var getTransactionsJob2: Job? = null

    init {
        getTransactions()
    }

    fun onEvent(event: TransactionListEvent) {
        when (event) {
            is TransactionListEvent.AddNewTransaction -> TODO()
            is TransactionListEvent.SelectTransaction -> TODO()
            is TransactionListEvent.DeleteTransaction -> {
                viewModelScope.launch {
                    transactionRepository.deleteTransaction(event.transaction)
                }
            }
        }
    }

//    @RequiresApi(Build.VERSION_CODES.N)
//    fun mergeResult(
//        transactionByDate: Map<Date?, MutableList<Transaction?>>,
//        transactions: List<Transaction>
//    ): Map<Date?, MutableList<Transaction?>> {
//        transactions.forEach(
//            Consumer { transaction: Transaction ->
//                (transactionByDate[transaction.date] ?: ArrayList()).add(transaction)
//            }
//        )
//        return transactionByDate
//    }

    private fun getTransactions() {
        getTransactionsJob?.cancel()
        getTransactionsJob2?.cancel()

        getTransactionsJob = transactionRepository.retrieveAllTransaction().onEach { transactions ->
            _state.value = state.value.copy(transactions = transactions)
        }.launchIn(viewModelScope)
    }
}
