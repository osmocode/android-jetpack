package fr.uge.plutus

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import dagger.hilt.android.AndroidEntryPoint
import fr.uge.plutus.data.DataStorageProvider
import fr.uge.plutus.ui.ant.AntTheme
import fr.uge.plutus.navigation.NavigationRouter


@AndroidEntryPoint
@OptIn(ExperimentalAnimationApi::class)
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val dark = DataStorageProvider.getInstance(LocalContext.current).getTheme()
                .collectAsState(initial = null)
            val navController = rememberAnimatedNavController()
            AntTheme(
                dark = dark.value
            ) {
                NavigationRouter(
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
    val dark = DataStorageProvider.getInstance(LocalContext.current).getTheme()
        .collectAsState(initial = null)
    val navController = rememberNavController()
    AntTheme(
        dark = dark.value
    ) {
        NavigationRouter(
            navController = navController
        )
    }
}