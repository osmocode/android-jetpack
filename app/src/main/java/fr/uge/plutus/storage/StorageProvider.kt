package fr.uge.plutus.storage

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.*
import androidx.hilt.navigation.compose.hiltViewModel
import fr.uge.plutus.ui.ant.AntTheme

@Composable
fun StorageProvider(
    system: Boolean = isSystemInDarkTheme(),
    storageViewModel: StorageViewModel = hiltViewModel(),
    content: @Composable () -> Unit
) {

    val dark = remember { mutableStateOf(system) }
    val wallet = remember { mutableStateOf(-1L) }

    LaunchedEffect(storageViewModel.state.value) {
        dark.value =
            if (storageViewModel.state.value.dark == null) system else storageViewModel.state.value.dark!!
        wallet.value =
            if (storageViewModel.state.value.wallet == null) -1 else storageViewModel.state.value.wallet!!
        SettingsDark.provides(dark.value)
        SettingsWallet.provides(wallet.value)
    }

    CompositionLocalProvider(
        SettingsDark provides dark.value,
        SettingsWallet provides wallet.value,
        content = {
            AntTheme(
                content = content
            )
        }
    )

}

object StorageProvider {

    val dark: Boolean
        @Composable
        @ReadOnlyComposable
        get() = SettingsDark.current

    val wallet: Long
        @Composable
        @ReadOnlyComposable
        get() = SettingsWallet.current

}

internal val SettingsDark = staticCompositionLocalOf { false }
internal val SettingsWallet = staticCompositionLocalOf { -1L }