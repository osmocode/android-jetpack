package fr.uge.plutus

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import fr.uge.plutus.ui.ant.Ant


@Composable
fun SplashScreen(
    isLoading: MutableState<Boolean>,
    navController: NavHostController
) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.LightGray),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Plutus",
            fontSize = 50.sp,
            color = Ant.colors.primary_text
        )
        Spacer(modifier = Modifier.padding(Ant.spacing.default))
        CircularProgressIndicator(
            modifier = Modifier.size(100.dp),
            strokeWidth = 8.dp,
            color = Ant.colors.primary_color_5
        )

    }
}


@Preview
@Composable
fun SplashScreenPreview() {
    val navController = rememberNavController()
    val isLoading = remember { mutableStateOf(true) }

    SplashScreen(isLoading = isLoading, navController = navController)
}