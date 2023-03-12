package fr.uge.plutus.layout.transactions_screen

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import fr.uge.plutus.data.repository.TransactionRepository
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TransactionsViewModel @Inject constructor(
    private val transactionRepository: TransactionRepository,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _state = mutableStateOf(TransactionsState())
    val state: State<TransactionsState> = _state

    init {
        savedStateHandle.get<Int>("wallet").let { wallet ->
            if (wallet != null) {
                transactionRepository.retrieveAllPastTransaction(wallet).onEach { past ->
                    _state.value = state.value.copy(
                        past = past
                    )
                }.launchIn(viewModelScope)

                transactionRepository.retrieveAllComingTransaction(wallet).onEach { coming ->
                    _state.value = state.value.copy(
                        coming = coming
                    )
                }.launchIn(viewModelScope)
            }
        }
    }

    fun onEvent(event: TransactionsEvent) {
        when (event) {
            is TransactionsEvent.TransactionsDelete -> viewModelScope.launch {
                val transactionTags = transactionRepository.retrieveTransactionWithTag(event.id)
                if (transactionTags != null) {
                    transactionRepository.deleteTransaction(transaction = transactionTags.transaction)
                }
            }
        }
    }
}