package fr.uge.plutus.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import fr.uge.plutus.data.DataBase
import fr.uge.plutus.data.dao.TransactionDao
import fr.uge.plutus.data.dao.WalletDao
import fr.uge.plutus.data.interfaces.ITransactionRepository
import fr.uge.plutus.data.interfaces.IWalletRepository
import fr.uge.plutus.data.repository.TransactionRepository
import fr.uge.plutus.data.repository.WalletRepository
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun providePlutusDatabase(
        @ApplicationContext app: Context
    ) = Room.databaseBuilder(
        app,
        DataBase::class.java,
        "Plutus.db"
    ).build()

    @Singleton
    @Provides
    fun provideTransactionDao(db: DataBase): TransactionDao {
        return db.transactionDao()
    }

    @Singleton
    @Provides
    fun provideTransactionRepository(dao: TransactionDao): ITransactionRepository {
        return TransactionRepository(dao)
    }

    @Singleton
    @Provides
    fun provideWalletDao(db: DataBase): WalletDao {
        return db.walletDao()
    }

    @Singleton
    @Provides
    fun provideWalletRepository(dao: WalletDao): IWalletRepository {
        return WalletRepository(dao)
    }

}