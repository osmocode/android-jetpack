package fr.uge.plutus

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import fr.uge.plutus.ui.ant.AntTheme
import fr.uge.plutus.widget.NavigationBar
import fr.uge.plutus.widget.NavigationRouter

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            AntTheme {
                Scaffold(
                    bottomBar = {
                        NavigationBar(
                            navController = navController
                        )
                    }
                ) {
                    NavigationRouter(
                        navController = navController
                    )
                }
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
            }
        ) {
            NavigationRouter(
                navController = navController
            )
        }
    }
}