package fr.uge.plutus.layout.wallet_screen

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import fr.uge.plutus.data.repository.BudgetRepository
import fr.uge.plutus.data.repository.TransactionRepository
import fr.uge.plutus.data.repository.WalletRepository
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WalletViewModel @Inject constructor(
    private val walletRepository: WalletRepository,
    private val transactionRepository: TransactionRepository,
    private val budgetRepository: BudgetRepository
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

            is WalletEvent.DeleteWallet -> viewModelScope.launch {
                val wallet = walletRepository.retrieveWallet(event.id)
                if (wallet != null) {
                    walletRepository.deleteWallet(wallet)
                }

            }

            is WalletEvent.CopyWallet -> viewModelScope.launch {
                val wallet = walletRepository.retrieveWallet(event.id)
                if (wallet != null) {
                    val walletDest = walletRepository.duplicateWallet("${wallet.name}_copy")
                    budgetRepository.duplicateBudget(
                        walletSrc = wallet.walletId!!,
                        walletDest = walletDest.toInt()
                    )
                    transactionRepository.duplicateTransactionWithTags(
                        walletSrc = wallet.walletId,
                        walletDest = walletDest.toInt()
                    )
                }


            }
        }
    }
}