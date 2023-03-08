package fr.uge.plutus.layout.home_screen


import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import fr.uge.plutus.data.repository.TransactionRepository
import fr.uge.plutus.storage.StorageRepository
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    transactionRepository: TransactionRepository,
    storageRepository: StorageRepository
) : ViewModel() {

    private val _state = mutableStateOf(HomeState())
    val state: State<HomeState> = _state

    init {
        storageRepository.wallet.onEach { wallet ->
            transactionRepository.retrieveLastTransaction(wallet!!, 3).onEach { transactions ->
                _state.value = state.value.copy(
                    transactions = transactions
                )
            }.launchIn(viewModelScope)
        }.launchIn(viewModelScope)
    }
}