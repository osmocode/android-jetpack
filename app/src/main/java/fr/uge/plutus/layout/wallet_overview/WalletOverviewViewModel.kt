package fr.uge.plutus.layout.wallet_overview

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import fr.uge.plutus.data.repository.WalletRepository
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class WalletOverviewViewModel @Inject constructor(
    private val walletRepository: WalletRepository
) : ViewModel() {

    private val _state = mutableStateOf(WalletOverviewState())
    val state: State<WalletOverviewState> = _state

    init {
        walletRepository.retrieveAllWallet().onEach { wallets ->
            _state.value = state.value.copy(wallets = wallets)
        }.launchIn(viewModelScope)
    }

}