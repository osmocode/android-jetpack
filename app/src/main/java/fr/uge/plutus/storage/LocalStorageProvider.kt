package fr.uge.plutus.storage

import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun LocalStorageProvider(
    localStorage: LocalStorageViewModel = hiltViewModel(),
    content: @Composable () -> Unit
) {

    val system = isSystemInDarkTheme()
    val ready = remember { mutableStateOf(false) }
    val dark = remember { mutableStateOf(false) }

    val darkState = localStorage.getDarkAsState()

    LaunchedEffect(localStorage.loaded.value) {
        if (localStorage.loaded.value) {
            ready.value = true
        }
    }

    LaunchedEffect(darkState.value) {
        dark.value = if (darkState.value == null) system else darkState.value!!
    }

    localStorage.load()

    if (ready.value) {
        CompositionLocalProvider(
            LocalStorageDark provides dark.value,
            LocalStorageWallet provides null,
            content = content
        )
    } else {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(color = Color.LightGray)
        ) {
            Text(
                text = "Loading...",
                fontSize = 50.sp,
                modifier = Modifier.align(Alignment.Center)
            )
        }
    }
}

object LocalStorageProvider {

    val dark: Boolean
        @Composable
        @ReadOnlyComposable
        get() = LocalStorageDark.current

    val wallet: Int?
        @Composable
        @ReadOnlyComposable
        get() = LocalStorageWallet.current

}

internal val LocalStorageDark = staticCompositionLocalOf { false }
internal val LocalStorageWallet = staticCompositionLocalOf<Int?> { null }