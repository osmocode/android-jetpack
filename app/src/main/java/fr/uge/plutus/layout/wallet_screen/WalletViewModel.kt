package fr.uge.plutus.layout.wallet_screen

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
class WalletViewModel @Inject constructor(
    private val walletRepository: WalletRepository,
) : ViewModel() {

    private val _state = mutableStateOf(WalletState())
    val state: State<WalletState> = _state

    init {
        walletRepository.retrieveAllWallet().onEach { wallets ->
            _state.value = state.value.copy(wallets = wallets)
        }.launchIn(viewModelScope)
    }

    fun onEvent(event: WalletEvent) {
        when (event) {
            is WalletEvent.CreateWallet -> viewModelScope.launch {
                walletRepository.createWallet(wallet = event.wallet)
            }

            is WalletEvent.UpdateWallet -> viewModelScope.launch {
                walletRepository.updateWallet(event.wallet)
            }
        }
    }
}