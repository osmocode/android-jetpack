package fr.uge.plutus.layout.transaction_new

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.navigation.animation.composable
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import fr.uge.plutus.navigation.NavigationRoute
import fr.uge.plutus.navigation.NavigationRouteHost
import fr.uge.plutus.pages.DatePage
import fr.uge.plutus.pages.NotePage
import fr.uge.plutus.pages.PricePage
import fr.uge.plutus.pages.TagPage
import fr.uge.plutus.ui.ant.Ant
import fr.uge.plutus.ui.components.*
import fr.uge.plutus.ui.input.AntDateInput
import fr.uge.plutus.ui.input.AntNoteInput

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun TransactionNewScreen(
    navController: NavHostController,
    viewModel: TransactionNewViewModel = hiltViewModel()
) {
    val state = viewModel.state.value
    val price = state.price
    val sheetVisible = remember { mutableStateOf(false) }
    val subNavController = rememberAnimatedNavController()

    NavigationRouteHost(
        navController = subNavController,
        startDestination = NavigationRoute.NewTransaction.route
    ) {
        composable(
            route = NavigationRoute.NewTransaction.route,
            content = {
                AntBottomSheetScaffold(
                    topBar = {
                        AntTopBar(
                            title = "Create transaction",
                            leadingIcons = listOf {
                                AntActionButton(
                                    icon = Icons.Outlined.ArrowBack,
                                    onClick = {
                                        navController.popBackStack()
                                    }
                                )
                            },
                        )
                    },
                    sheetPeekHeight = 0.dp,
                    sheetVisible = sheetVisible,
                    sheetContent = {}
                ) {
                    Column(
                        modifier = Modifier.padding(horizontal = Ant.spacing.default),
                        verticalArrangement = Arrangement.spacedBy(Ant.spacing.default)
                    ) {
                        Spacer(modifier = Modifier.size(Ant.spacing.default))
                        AntAmountInput(
                            price = price,
                            onClick = {
                                subNavController.navigate(NavigationRoute.Price.route)
                            }
                        )
                        AntTagInput (
                            onClick = {
                                subNavController.navigate(NavigationRoute.Tag.route)
                            }
                        )
                        AntNoteInput (
                            onClick = {
                                subNavController.navigate(NavigationRoute.Note.route)
                            }
                        )
                        AntDateInput (
                            onClick = {
                                subNavController.navigate(NavigationRoute.Date.route)
                            }
                        )
                    }
                }
            }
        )
        composable(
            route = NavigationRoute.Price.route,
            content = {
                PricePage(
                    navController = subNavController
                )
            }
        )
        composable(
            route = NavigationRoute.Tag.route,
            content = {
                TagPage(
                    navController = subNavController
                )
            }
        )
        composable(
            route = NavigationRoute.Note.route,
            content = {
                NotePage(
                    navController = subNavController
                )
            }
        )
        composable(
            route = NavigationRoute.Date.route,
            content = {
                DatePage(
                    navController = subNavController
                )
            }
        )
    }
}

@Preview
@Composable
fun TransactionNewScreenPreview(

) {
    val navController = rememberNavController()
    TransactionNewScreen(
        navController = navController
    )
}