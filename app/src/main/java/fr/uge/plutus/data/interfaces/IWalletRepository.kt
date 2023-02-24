package fr.uge.plutus.data.interfaces

import fr.uge.plutus.data.model.Wallet
import kotlinx.coroutines.flow.Flow

interface IWalletRepository {

    fun retrieveAllWallet(): Flow<List<Wallet>>

    suspend fun createWallet(wallet: Wallet)

    suspend fun retrieveWallet(id: Int): Wallet?

    suspend fun updateWallet(wallet: Wallet): Int

    suspend fun deleteWallet(wallet: Wallet): Int
}