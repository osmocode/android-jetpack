package fr.uge.plutus.storage

import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LocalStorageViewModel @Inject constructor(
    private val localStorage: LocalStorage
) : ViewModel() {

    private val _state = mutableStateOf(LocalStorageState())
    private var state: State<LocalStorageState> = _state

    val loaded = mutableStateOf(false)

    fun load() {
        viewModelScope.launch {
            val dark = launch { _state.value = state.value.copy(dark = localStorage.getDark()) }
            val wallet =
                launch { _state.value = state.value.copy(wallet = localStorage.getWallet()) }
            dark.join()
            wallet.join()
            loaded.value = true
        }
    }

    @Composable
    fun getDarkAsState(): State<Boolean?> =
        localStorage.getDarkAsState().collectAsState(initial = state.value.dark)

    @Composable
    fun getWallet(): State<Int?> =
        localStorage.getWalletAsState().collectAsState(initial = state.value.wallet)

    fun setDark(value: Boolean?) {
        viewModelScope.launch {
            localStorage.setDark(value)
        }
    }

}