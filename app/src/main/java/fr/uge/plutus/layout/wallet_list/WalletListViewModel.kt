package fr.uge.plutus.layout.wallet_list

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import fr.uge.plutus.data.repository.WalletRepository
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WalletListViewModel @Inject constructor(
    private val walletRepository: WalletRepository,
) : ViewModel() {


    private val _state = mutableStateOf(WalletListState())
    val state: State<WalletListState> = _state

    init {
        walletRepository.retrieveAllWallet().onEach { wallets ->
            _state.value = state.value.copy(wallets = wallets)
        }.launchIn(viewModelScope)
    }

    fun onEvent(event: WalletListEvent) {
        when (event) {
            is WalletListEvent.CreateWallet -> {
                viewModelScope.launch {
                    walletRepository.createWallet(wallet = event.wallet)
                }
            }
            is WalletListEvent.DeleteWallet -> {
                viewModelScope.launch {
                    walletRepository.deleteWallet(event.wallet)
                }
            }
        }
    }

}