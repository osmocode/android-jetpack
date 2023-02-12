package fr.uge.plutus

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import dagger.hilt.android.AndroidEntryPoint
import fr.uge.plutus.ui.ant.AntTheme
import fr.uge.plutus.widget.NavigationBar
import fr.uge.plutus.widget.NavigationRouter

@AndroidEntryPoint
@OptIn(ExperimentalAnimationApi::class)
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberAnimatedNavController()
            AntTheme {
                NavigationRouter(navController = navController)
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
    val navController = rememberNavController()
    AntTheme {
        Scaffold(
            bottomBar = {
                NavigationBar(
                    navController = navController
                )
            },
            content = {
                NavigationRouter(
                    navController = navController
                )
            }
        )
    }
}