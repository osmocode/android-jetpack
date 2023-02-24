package fr.uge.plutus

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import dagger.hilt.android.AndroidEntryPoint
import fr.uge.plutus.ui.ant.AntTheme
import fr.uge.plutus.navigation.NavigationRouter
import fr.uge.plutus.storage.LocalStorageProvider

@AndroidEntryPoint
@OptIn(ExperimentalAnimationApi::class)
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberAnimatedNavController()
            LocalStorageProvider(
                splashScreen = { loading ->
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
                },
                content = {
                    AntTheme(
                        content = {
                            NavigationRouter(
                                navController = navController
                            )
                        }
                    )
                }
            )
        }
    }
}

@OptIn(ExperimentalAnimationApi::class)
@Preview(
    showBackground = true,
    widthDp = 412,
    heightDp = 915
)
@Composable
fun DefaultPreview() {
    val navController = rememberAnimatedNavController()
    AntTheme(
        content = {
            NavigationRouter(
                navController = navController
            )
        }
    )
}