package fr.uge.plutus.layout.wallet_list

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import fr.uge.plutus.data.model.Wallet
import fr.uge.plutus.ui.ant.Ant
import fr.uge.plutus.ui.components.*
import fr.uge.plutus.ui.field.AntTextField

@Composable
fun WalletListPage(
    navController: NavHostController
) {
    val sheetVisible = remember { mutableStateOf(false) }
    val walletName = remember { mutableStateOf("") }

    val demoWallets = listOf(
        Wallet(
            id = 1,
            name = "Personal"
        ),
        Wallet(
            id = 2,
            name = "Professional"
        ),
        Wallet(
            id = 3,
            name = "Gogo"
        )
    )

    AntBottomSheetScaffold(
        sheetPeekHeight = 0.dp,
        sheetVisible = sheetVisible,
        sheetContent = {
            Column(
                modifier = Modifier.padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(Ant.spacing.default)
            ) {
                // Ajoutez les champs d'entrée de texte pour le nom et le solde du portefeuille
                AntTextField(
                    placeHolder = "Wallet Name",
                    onChange = { walletName.value = it }
                )

                // Ajoutez un bouton de création pour créer un nouveau portefeuille
                CustomButton(
                    title = "Create Wallet",
                    type = CustomButtonType.PRIMARY,
                    onClick = {
                        // Code pour créer un nouveau portefeuille
                        sheetVisible.value = false // Fermer la feuille après la création
                    }

                )
            }
        },
        topBar = {
            AntTopBar(
                title = "Wallets",
                leadingIcons = listOf {
                    AntActionButton(
                        icon = Icons.Outlined.ArrowBack,
                        onClick = {
                            navController.popBackStack()
                        }
                    )
                },
                trailingIcons = listOf {
                    AntActionButton(
                        icon = Icons.Outlined.Add,
                        title = "New Wallet",
                        type = AntActionButtonType.PRIMARY,
                        onClick = {
                            sheetVisible.value = !sheetVisible.value
                        }
                    )
                }
            )
        }
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            AntTextField(
                modifier = Modifier.padding(horizontal = Ant.spacing.default),
                leadingIcon = Icons.Outlined.Search,
                onChange = {}
            )
            LazyColumn(
                modifier = Modifier.padding(horizontal = Ant.spacing.default),
            ) {
                items(demoWallets) { wallet ->
                    AntCard(
                        title = wallet.name,
                        description = "Wallet id: ${wallet.id}",
                        extras = "",
                        leadingIcon = listOf(
                            AntCardActionItem(
                                icon = Icons.Outlined.Delete,
                                color = Ant.colors.primary_text,
                                onClick = {},
                            )
                        ),
                        trailingIcon = listOf(
                            AntCardActionItem(
                                icon = Icons.Outlined.FileCopy,
                                color = Ant.colors.primary_text,
                                onClick = {},
                            )
                        )
                    )
                }
            }
        }
    }
}

@Preview(
    showBackground = true
)
@Composable
fun WalletListPagePreview(

) {
    val navController = rememberNavController()
    WalletListPage(
        navController = navController
    )
}
