package fr.uge.plutus.layout.settings_screen

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import fr.uge.plutus.storage.StorageRepository
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(
    private val storageRepository: StorageRepository
) : ViewModel() {

    private val _state = mutableStateOf(SettingsState())
    val state: State<SettingsState> = _state

    init {
        storageRepository.dark.onEach { dark ->
            _state.value = state.value.copy(
                dark = dark
            )
        }.launchIn(viewModelScope)
    }

    fun onEvent(event: SettingsEvent) {
        when (event) {
            is SettingsEvent.SetDarkTheme -> viewModelScope.launch {
                storageRepository.dark(event.value)
            }
        }
    }

}