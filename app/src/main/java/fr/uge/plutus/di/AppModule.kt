package fr.uge.plutus.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import fr.uge.plutus.data.DataBase
import fr.uge.plutus.data.dao.BudgetDao
import fr.uge.plutus.data.dao.TagDao
import fr.uge.plutus.data.dao.TransactionDao
import fr.uge.plutus.data.interfaces.IBudgetRepository
import fr.uge.plutus.data.interfaces.ITagRepository
import fr.uge.plutus.data.interfaces.ITransactionRepository
import fr.uge.plutus.data.repository.BudgetRepository
import fr.uge.plutus.data.repository.TagRepository
import fr.uge.plutus.data.dao.WalletDao
import fr.uge.plutus.data.interfaces.ITransactionRepository
import fr.uge.plutus.data.interfaces.IWalletRepository
import fr.uge.plutus.data.repository.TransactionRepository
import fr.uge.plutus.storage.LocalDateStore
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
    fun provideLocalDataStore(
        @ApplicationContext context: Context
    ): LocalDateStore {
        return LocalDateStore(context = context)
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



    @Singleton
    @Provides
    fun provideTagDao(db: DataBase): TagDao {
        return db.tagDao()
    }

    @Singleton
    @Provides
    fun provideTagRepository(dao: TagDao): ITagRepository {
        return TagRepository(dao)
    }


    @Singleton
    @Provides
    fun provideBudgetDao(db: DataBase): BudgetDao {
        return db.budgetDao()
    }

    @Singleton
    @Provides
    fun provideBudgetRepository(dao: BudgetDao): IBudgetRepository {
        return BudgetRepository(dao)
    }
}