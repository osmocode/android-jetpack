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
            enum class Type {
                CREATE, UPDATE, DUPLICATE;

                override fun toString() = this.name
            }

            fun createCredit() = "transaction/CREATE/CREDIT/-1"
            fun createDebit() = "transaction/CREATE/DEBIT/-1"
            fun createTransfer() = "transaction/CREATE/TRANSFER/-1"

            fun updateTransaction(transaction: Transaction): String {
                return when (transaction.type as Transaction.Type) {
                    Transaction.Type.CREDIT -> "transaction/UPDATE/CREDIT/${transaction.id}"
                    Transaction.Type.DEBIT -> "transaction/UPDATE/DEBIT/${transaction.id}"
                    Transaction.Type.TRANSFER -> "transaction/UPDATE/TRANSFER/${transaction.id}"
                }
            }

            fun duplicateTransaction(transaction: Transaction): String {
                return when (transaction.type as Transaction.Type) {
                    Transaction.Type.CREDIT -> "transaction/DUPLICATE/CREDIT/${transaction.id}"
                    Transaction.Type.DEBIT -> "transaction/DUPLICATE/DEBIT/${transaction.id}"
                    Transaction.Type.TRANSFER -> "transaction/DUPLICATE/TRANSFER/${transaction.id}"
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