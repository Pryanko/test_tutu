package ru.tutu.stations.localdb

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import ru.tutu.stations.localdb.room.dao.CountryDao
import ru.tutu.stations.localdb.room.dao.StationDao
import ru.tutu.stations.localdb.room.entity.CountryEntity
import ru.tutu.stations.localdb.room.entity.StationEntity

/**
 * @author Grigoriy Pryamov.
 */
@Database(
    version = BuildConfig.DB_VERSION, exportSchema = false,
    entities = [
        CountryEntity::class,
        StationEntity::class
    ]
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun countryDao(): CountryDao

    abstract fun stationDao(): StationDao
}