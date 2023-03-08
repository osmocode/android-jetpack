package fr.uge.plutus

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import fr.uge.plutus.ui.ant.Ant
import kotlinx.coroutines.delay


@Composable
fun SplashScreen(
    system: Boolean = isSystemInDarkTheme(),
    ready: MutableState<Boolean>,
    onFinish: () -> Unit
) {
    var animation by remember { mutableStateOf(false) }
    val animateAlpha = animateFloatAsState(
        targetValue = if (!animation) 1f else 0f,
        animationSpec = tween(
            durationMillis = 500
        )
    )
    val animateSize = animateDpAsState(
        targetValue = if (!animation) 80.dp else 0.dp,
        animationSpec = tween(
            durationMillis = 500
        )
    )

    LaunchedEffect(ready.value) {
        if (ready.value) {
            delay(300)
            animation = true
            delay(600)
            onFinish.invoke()
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = if (system) Color.DarkGray else Color.White),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Plutus",
            fontSize = 50.sp,
            color = if (system) Color.White else Color.DarkGray
        )
        Spacer(modifier = Modifier.padding(Ant.spacing.default))
        CircularProgressIndicator(
            modifier = Modifier
                .size(animateSize.value)
                .alpha(animateAlpha.value),
            strokeWidth = 8.dp,
            color = if (system) Color.White else Color.DarkGray
        )
    }
}