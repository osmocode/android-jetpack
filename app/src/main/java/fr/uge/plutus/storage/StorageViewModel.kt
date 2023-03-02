package fr.uge.plutus.storage

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class StorageViewModel @Inject constructor(
    private val storageRepository: StorageRepository
) : ViewModel() {

    private val _ready = mutableStateOf(false)
    val ready: State<Boolean> = _ready

    private val _state = mutableStateOf(StorageState())
    val state: State<StorageState> = _state

    init {
        storageRepository.dark.combine(
            storageRepository.wallet
        ) { dark, wallet ->
            _state.value = state.value.copy(
                dark = dark,
                wallet = wallet
            )
            _ready.value = true
        }.launchIn(viewModelScope)
    }

    fun setDark(value: Boolean?) = viewModelScope.launch {
        storageRepository.dark(value = value)
    }

    fun setWallet(value: Int?) = viewModelScope.launch {
        storageRepository.wallet(value = value)
    }

}