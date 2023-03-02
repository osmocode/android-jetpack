package fr.uge.plutus

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.*
import androidx.compose.ui.tooling.preview.Preview
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import dagger.hilt.android.AndroidEntryPoint
import fr.uge.plutus.navigation.NavigationRoot

@AndroidEntryPoint
@OptIn(ExperimentalAnimationApi::class)
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberAnimatedNavController()
            NavigationRoot(navController = navController)
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
    /*
    val navController = rememberAnimatedNavController()
    NavigationRouter(
        navController = navController
    )
     */
}