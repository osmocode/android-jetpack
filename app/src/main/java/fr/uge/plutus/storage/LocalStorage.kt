package fr.uge.plutus.storage

import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LocalStorage @Inject constructor(
    private val localDateStore: LocalDateStore
) : ViewModel() {

    private val _buffer = mutableStateOf(LocalStorageState())
    var buffer: State<LocalStorageState> = _buffer
    val ready = mutableStateOf(false)

    fun loader() {
        viewModelScope.launch {
            val dark = localDateStore.getDark().first()
            val wallet = localDateStore.getWallet().first()
            _buffer.value = buffer.value.copy(
                dark = dark,
                wallet = wallet
            )
            ready.value = true
        }
    }

    @Composable
    fun getDark(): State<Boolean?> =
        localDateStore.getDark().collectAsState(initial = buffer.value.dark)

    @Composable
    fun getWallet(): State<Int?> =
        localDateStore.getWallet().collectAsState(initial = buffer.value.wallet)

    fun setDark(value: Boolean?) {
        viewModelScope.launch {
            localDateStore.setDark(value)
        }
    }

    fun setWallet(value: Int?) {
        viewModelScope.launch {
            localDateStore.setWallet(value)
        }
    }

}