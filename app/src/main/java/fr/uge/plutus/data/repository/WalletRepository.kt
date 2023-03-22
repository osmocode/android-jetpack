package fr.uge.plutus.data.repository

import fr.uge.plutus.data.dao.WalletDao
import fr.uge.plutus.data.interfaces.IWalletRepository
import fr.uge.plutus.data.model.Wallet
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class WalletRepository @Inject constructor(
    private val walletDao: WalletDao
) : IWalletRepository {
    override fun retrieveAllWallet(): Flow<List<Wallet>> = walletDao.retrieveAll()

    override suspend fun createWallet(wallet: Wallet) =
        walletDao.create(wallet)

    override suspend fun retrieveWallet(id: Long): Wallet? =
        walletDao.retrieveById(id)

    override suspend fun updateWallet(wallet: Wallet): Int =
        walletDao.update(wallet)

    override suspend fun deleteWallet(wallet: Wallet): Int =
        walletDao.delete(wallet)

}