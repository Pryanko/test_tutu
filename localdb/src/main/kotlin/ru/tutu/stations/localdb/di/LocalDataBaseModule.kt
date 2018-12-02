package ru.tutu.stations.localdb.di

import android.arch.persistence.room.Room
import android.content.Context
import dagger.Module
import dagger.Provides
import ru.tutu.stations.localdb.AppDatabase
import ru.tutu.stations.localdb.base.DbTransaction
import ru.tutu.stations.localdb.base.DbTransactionImpl
import javax.inject.Singleton

/**
 * @author Grigoriy Pryamov.
 */
@Module(includes = [RepositoryModule::class, MapperModule::class])
class LocalDataBaseModule {

    @Provides
    @Singleton
    fun appDataBase(context: Context): AppDatabase {
        return Room.databaseBuilder(context, AppDatabase::class.java, DB_NAME)
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    @Singleton
    fun dbTransaction(appDatabase: AppDatabase): DbTransaction = DbTransactionImpl(appDatabase)

    companion object {
        const val DB_NAME = "local.db"
    }
}