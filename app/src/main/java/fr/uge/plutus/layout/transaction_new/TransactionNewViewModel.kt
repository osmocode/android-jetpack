package fr.uge.plutus.layout.transaction_new

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import fr.uge.plutus.data.repository.TransactionRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TransactionNewViewModel @Inject constructor(
    private val transactionRepository: TransactionRepository,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _state = mutableStateOf(TransactionNewState())
    val state: State<TransactionNewState> = _state

    private var currentTransactionId: Int? = null

    init {
        savedStateHandle.get<String>("transactionType")?.let { transactionType ->
            Log.println(Log.ASSERT, "DEBUG", "type= $transactionType")
            _state.value = state.value.copy(type = transactionType)
        }
        savedStateHandle.get<Int>("transactionId")?.let { transactionId ->
            Log.println(Log.ASSERT, "DEBUG", "id= $transactionId")
            if (transactionId != -1) {
                viewModelScope.launch {
                    transactionRepository.retrieveTransaction(transactionId)
                        ?.also { transaction ->
                            currentTransactionId = transaction.id
                            _state.value = state.value.copy(
                                title = transaction.title,
                                description = transaction.description,
                                price = transaction.price,
                            )
                        }
                }
            }
        }
    }

    fun onEvent(event: TransactionNewEvent) {
        when (event) {
            is TransactionNewEvent.EditTitle -> {
                _state.value = state.value.copy(title = event.title)
            }
            is TransactionNewEvent.EditDescription -> {
                _state.value = state.value.copy(description = event.description)
            }
            is TransactionNewEvent.EditPrice -> {
                _state.value = state.value.copy(price = event.price)
            }
            is TransactionNewEvent.SubmitTransaction -> {
                viewModelScope.launch {
                    //TODO: add some verifications before storing in the database
                    transactionRepository.createTransaction(event.transaction)
                }
            }
        }
    }
}
