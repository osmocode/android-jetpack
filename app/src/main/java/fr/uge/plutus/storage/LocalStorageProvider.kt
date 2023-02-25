package fr.uge.plutus.storage

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.*
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun LocalStorageProvider(
    localStorage: LocalStorage = hiltViewModel(),
    system: Boolean = isSystemInDarkTheme(),
    isReady: (ready: Boolean) -> Unit,
    content: @Composable () -> Unit
) {
    // Start to provide value when set
    //val ready = remember { mutableStateOf(false) }
    // Sub provided values
    val dark = remember { mutableStateOf(system) }
    val wallet = remember { mutableStateOf(-1) }

    val darkProvider = localStorage.getDark()
    val walletBuffer = localStorage.getWallet()

    LaunchedEffect(darkProvider.value) {
        dark.value = if (darkProvider.value == null) system else darkProvider.value!!
    }

    LaunchedEffect(walletBuffer.value) {
        wallet.value = if (walletBuffer.value == null) -1 else walletBuffer.value!!
    }

    // Local Storage initial load handler
    LaunchedEffect(localStorage.ready.value) {
        if (localStorage.ready.value) {
            // Set default value of provider values
            dark.value =
                if (localStorage.buffer.value.dark == null) system else localStorage.buffer.value.dark!!
            wallet.value =
                if (walletBuffer.value == null) -1 else walletBuffer.value!!
        }
        isReady.invoke(localStorage.ready.value)
    }

    // Start to load from local storage
    localStorage.loader()

    CompositionLocalProvider(
        LocalStorageDark provides dark.value,
        LocalStorageWallet provides wallet.value,
        content = content
    )
}

object LocalStorageProvider {

    val dark: Boolean
        @Composable
        @ReadOnlyComposable
        get() = LocalStorageDark.current

    val wallet: Int
        @Composable
        @ReadOnlyComposable
        get() = LocalStorageWallet.current

}

internal val LocalStorageDark = staticCompositionLocalOf { false }
internal val LocalStorageWallet = staticCompositionLocalOf { -1 }