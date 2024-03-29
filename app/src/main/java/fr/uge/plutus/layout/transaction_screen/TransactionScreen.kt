package fr.uge.plutus.layout.transaction_screen


import android.content.Context
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import fr.uge.plutus.data.model.Price
import fr.uge.plutus.layout.transaction_screen.layout.*
import fr.uge.plutus.navigation.*
import fr.uge.plutus.storage.SettingsWallet
import fr.uge.plutus.ui.ant.Ant
import fr.uge.plutus.ui.components.*
import fr.uge.plutus.ui.input.AntDateInput
import fr.uge.plutus.ui.input.AntLocationInput
import fr.uge.plutus.ui.input.AntNoteInput
import fr.uge.plutus.ui.input.AntTagInput

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun TransactionScreen(
    navHostController: NavHostController,
    sheetVisible: MutableState<Boolean>,
    viewModel: TransactionViewModel = hiltViewModel()
) {
    val navController = rememberAnimatedNavController()

    AnimatedNavHost(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Ant.colors.background),
        navController = navController,
        enterTransition = enterTransition(),
        exitTransition = exitTransition(),
        popEnterTransition = popEnterTransition(),
        popExitTransition = popExitTransition(),
        startDestination = NavigationRoute.MainScreen.TransactionScreen.TransactionOverview.route
    ) {
        composable(
            route = NavigationRoute.MainScreen.TransactionScreen.TransactionOverview.route,
            popExitTransition = {
                slideOutHorizontally(
                    targetOffsetX = { width -> width },
                    animationSpec = tween(300)
                )
            },
            content = {
                TransactionScreenOverview(
                    navHostController = navHostController,
                    navController = navController,
                    sheetVisible = sheetVisible,
                    viewModel = viewModel
                )
            }
        )
        composable(
            route = NavigationRoute.MainScreen.TransactionScreen.AmountLayout.route,
            content = {
                AmountLayout(
                    navController = navController,
                    viewModel = viewModel
                )
            }
        )
        composable(
            route = NavigationRoute.MainScreen.TransactionScreen.TagLayout.route,
            content = {
                TagLayout(
                    navController = navController,
                    viewModel = viewModel
                )
            }
        )
        composable(
            route = NavigationRoute.MainScreen.TransactionScreen.DateLayout.route,
            content = {
                DateLayout(
                    navController = navController,
                    viewModel = viewModel
                )
            }
        )
        composable(
            route = NavigationRoute.MainScreen.TransactionScreen.DescLayout.route,
            content = {
                DescLayout(
                    navController = navController,
                    viewModel = viewModel
                )
            }
        )
        composable(
            route = NavigationRoute.MainScreen.TransactionScreen.LocationLayout.route,
            content = {
                LocationLayout(
                    navController = navController,
                    viewModel = viewModel
                )
            }
        )
    }
}

@Composable
fun TransactionScreenOverview(
    navHostController: NavHostController,
    navController: NavHostController,
    sheetVisible: MutableState<Boolean>,
    viewModel: TransactionViewModel,
    context: Context = LocalContext.current.applicationContext
) {
    val wallet = SettingsWallet.current

    AntBottomSheetScaffold(
        sheetPeekHeight = 0.dp,
        sheetVisible = sheetVisible,
        sheetContent = { /*TODO*/ },
        topBar = {
            AntTopBar(
                sheetVisible = sheetVisible,
                title = transactionScreenTitle(viewModel.state.value.action) + " a transaction",
                leadingIcons = listOf {
                    AntActionButton(
                        icon = Icons.Outlined.ArrowBack,
                        onClick = {
                            navHostController.popBackStack()
                        }
                    )
                }
            )
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(Ant.spacing.default),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Column(
                verticalArrangement = Arrangement.spacedBy(Ant.spacing.default)
            ) {
                AntAmountInput(
                    price = Price(
                        currency = viewModel.state.value.transactionWithTags.transaction.price.currency,
                        amount = viewModel.state.value.transactionWithTags.transaction.price.amount
                    ),
                    onClick = { navController.navigate(route = NavigationRoute.MainScreen.TransactionScreen.AmountLayout.route) }
                )
                AntTagInput(
                    tags = viewModel.state.value.newTags,
                    onClick = { navController.navigate(route = NavigationRoute.MainScreen.TransactionScreen.TagLayout.route) }
                )
                AntNoteInput(
                    title = viewModel.state.value.transactionWithTags.transaction.title.ifEmpty { "Title" },
                    description = viewModel.state.value.transactionWithTags.transaction.description.ifEmpty { "Description" },
                    onClick = { navController.navigate(route = NavigationRoute.MainScreen.TransactionScreen.DescLayout.route) }
                )
                AntDateInput(
                    timestamp = viewModel.state.value.transactionWithTags.transaction.timestamp,
                    onClick = { navController.navigate(route = NavigationRoute.MainScreen.TransactionScreen.DateLayout.route) }
                )
                AntLocationInput (
                    onClick = { navController.navigate(route = NavigationRoute.MainScreen.TransactionScreen.LocationLayout.route) }
                )
            }
            CustomButton(
                title = transactionScreenTitle(viewModel.state.value.action),
                type = CustomButtonType.PRIMARY,
                onClick = {
                    viewModel.onEvent(TransactionEvent.TransactionSubmit(wallet = wallet))
                    navHostController.popBackStack()
                }
            )
        }
    }
}

fun transactionScreenTitle(
    action: String
): String {
    return action[0].uppercase() + action.substring(1).lowercase()
}
