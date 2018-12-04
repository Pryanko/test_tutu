package ru.tutu.stations.localdb

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import ru.tutu.stations.localdb.room.CountryDao
import ru.tutu.stations.localdb.room.entity.CountryEntity

/**
 * @author Grigoriy Pryamov.
 */
@Database(
    version = BuildConfig.DB_VERSION, exportSchema = false,
    entities = [CountryEntity::class
    ]
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun countryDao(): CountryDao
}