package fr.uge.plutus.layout.home_screen.layout

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun IncomingLayout(

) {
    Box(modifier = Modifier.fillMaxSize()) {
        Text(text = "Incoming layout")
    }
}