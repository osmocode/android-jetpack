package fr.uge.plutus

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import dagger.hilt.android.AndroidEntryPoint
import fr.uge.plutus.ui.ant.AntTheme
import fr.uge.plutus.navigation.NavigationRouter


@AndroidEntryPoint
@OptIn(ExperimentalAnimationApi::class)
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val defaultMode = isSystemInDarkTheme()
            val darkMode = remember { mutableStateOf(defaultMode) }
            val navController = rememberAnimatedNavController()
            AntTheme(
                darkTheme = darkMode.value,
            ) {
                NavigationRouter(
                    darkMode = darkMode,
                    navController = navController
                )
            }
        }
    }
}

@Preview(
    showBackground = true,
    widthDp = 412,
    heightDp = 915
)
@Composable
fun DefaultPreview() {
    val defaultMode = isSystemInDarkTheme()
    val darkMode = remember { mutableStateOf(defaultMode) }
    val navController = rememberNavController()
    AntTheme(
        darkTheme = darkMode.value
    ){
        NavigationRouter(
            darkMode = darkMode,
            navController = navController
        )
    }
}