package fr.uge.plutus.navigation

import fr.uge.plutus.data.model.Transaction

sealed class NavigationRoute(
    val route: String
) {
    object SplashScreen : NavigationRoute(route = "splash")
    object WalletScreen : NavigationRoute(route = "wallets")
    object MainScreen : NavigationRoute(route = "main") {
        object HomeScreen : NavigationRoute(route = "home")
        object SearchScreen : NavigationRoute(route = "search")
        object WalletScreen : NavigationRoute(route = "wallet")
        object SettingsScreen : NavigationRoute(route = "settings")
        object TransactionsScreen : NavigationRoute(route = "transactions/{wallet}") {
            fun route(wallet: Int) = "transactions/${wallet}"
        }

        object TransactionScreen : NavigationRoute(route = "transaction/{action}/{type}/{id}") {

            fun createTransaction(type: Transaction.Type): String {
                return when (type) {
                    Transaction.Type.CREDIT -> "transaction/CREATE/CREDIT/-1"
                    Transaction.Type.DEBIT -> "transaction/CREATE/DEBIT/-1"
                    Transaction.Type.TRANSFER -> "transaction/CREATE/TRANSFER/-1"
                }
            }

            fun updateTransaction(transaction: Transaction): String {
                return when (Transaction.Type.valueOf(transaction.type)) {
                    Transaction.Type.CREDIT -> "transaction/UPDATE/CREDIT/${transaction.transactionId}"
                    Transaction.Type.DEBIT -> "transaction/UPDATE/DEBIT/${transaction.transactionId}"
                    Transaction.Type.TRANSFER -> "transaction/UPDATE/TRANSFER/${transaction.transactionId}"
                }
            }

            fun duplicateTransaction(transaction: Transaction): String {
                return when (Transaction.Type.valueOf(transaction.type)) {
                    Transaction.Type.CREDIT -> "transaction/DUPLICATE/CREDIT/${transaction.transactionId}"
                    Transaction.Type.DEBIT -> "transaction/DUPLICATE/DEBIT/${transaction.transactionId}"
                    Transaction.Type.TRANSFER -> "transaction/DUPLICATE/TRANSFER/${transaction.transactionId}"
                }
            }

            object TransactionOverview : NavigationRoute(route = "overview")
            object AmountLayout : NavigationRoute(route = "amount")
            object TagLayout : NavigationRoute(route = "tags")
            object DescLayout : NavigationRoute(route = "desc")
            object DateLayout : NavigationRoute(route = "date")

        }
    }
}