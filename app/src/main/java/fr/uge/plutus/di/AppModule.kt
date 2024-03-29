package fr.uge.plutus.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import fr.uge.plutus.data.DataBase
import fr.uge.plutus.data.dao.*
import fr.uge.plutus.data.interfaces.*
import fr.uge.plutus.data.repository.*
import fr.uge.plutus.notification.TransactionNotification
import fr.uge.plutus.storage.StorageRepository
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
    fun provideStorageRepository(
        @ApplicationContext context: Context
    ): StorageRepository {
        return StorageRepository(context = context)
    }

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

    @Singleton
    @Provides
    fun provideTransactionNotification(
        @ApplicationContext context: Context
    ): TransactionNotification {
        return TransactionNotification(context)
    }

    /*
    @Singleton
    @Provides
    fun provideNotificationAlarm(
        @ApplicationContext context: Context
    ): AlarmManager {
        val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        return alarmManager
    }

    @Singleton
    @Provides
    fun provideNotificationManager(
        @ApplicationContext context: Context
    ): NotificationManagerCompat {
        val notificationManager = NotificationManagerCompat.from(context)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            val channel = NotificationChannel(
                "NOTIFICATION_CHANNEL",
                "Plutus notification",
                NotificationManager.IMPORTANCE_DEFAULT
            )
            notificationManager.createNotificationChannel(channel)
        }
        return notificationManager
    }

    @Singleton
    @Provides
    fun provideNotificationBuilder(
        @ApplicationContext context: Context
    ): NotificationCompat.Builder {
        return NotificationCompat.Builder(context, "NOTIFICATION_CHANNEL")
            .setContentTitle("Plutus")
            .setSmallIcon(android.R.drawable.ic_menu_info_details)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setVisibility(NotificationCompat.VISIBILITY_PUBLIC)
    }
     */
}