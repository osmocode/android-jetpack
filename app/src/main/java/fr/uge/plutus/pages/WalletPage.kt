package fr.uge.plutus.pages

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import fr.uge.plutus.navigation.NavigationRoute
import fr.uge.plutus.ui.ant.Ant
import fr.uge.plutus.ui.components.*

@Composable
fun WalletPage(
    navController: NavHostController
) {
    val sheetVisible = remember { mutableStateOf(false) }
    val wallets = listOf(
        AntWalletCardItem(
            label = "Personal",
            desc = "Current"
        ),
        AntWalletCardItem(
            label = "Pro",
            desc = "Not used"
        ),
    )

    AntBottomSheetScaffold(
        topBar = {
            AntTopBar(
                title = "Wallets",
                trailingIcons = listOf {
                    AntActionButton(
                        icon = Icons.Outlined.Add,
                        type = AntActionButtonType.PRIMARY,
                        title = "Add",
                        onClick = {
                            navController.navigate(NavigationRoute.NewWallet.route)
                        }
                    )
                }
            )
        },
        sheetPeekHeight = 0.dp,
        sheetVisible = sheetVisible,
        sheetContent = {}
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(Ant.spacing.small)
        ){
            AntWalletCardList(items = wallets)
        }
    }
}

@Preview
@Composable
fun WalletPagePreview(

) {
    val navController = rememberNavController()
    WalletPage(
        navController = navController
    )
}