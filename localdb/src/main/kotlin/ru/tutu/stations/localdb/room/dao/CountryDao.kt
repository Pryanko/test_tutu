package ru.tutu.stations.localdb.room.dao

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Query
import ru.tutu.stations.localdb.room.dao.base.BaseDao
import ru.tutu.stations.localdb.room.entity.CountryEntity

/**
 * @author Grigoriy Pryamov.
 */
@Dao
interface CountryDao : BaseDao<CountryEntity> {
    @Query("SELECT * FROM country")
    fun getAllCountries(): List<CountryEntity>

    @Query("SELECT * FROM country WHERE countryTitleToLower = :nameToLower")
    fun containsCountry(nameToLower: String): CountryEntity?
}