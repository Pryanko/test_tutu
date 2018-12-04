package ru.tutu.stations.localdb.room.dao

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Query
import ru.tutu.stations.localdb.room.dao.base.BaseDao
import ru.tutu.stations.localdb.room.entity.StationEntity

/**
 * @author Grigoriy Pryamov.
 */
@Dao
interface StationDao : BaseDao<StationEntity> {

    @Query("SELECT * FROM Station WHERE countryId = :countryId")
    fun getAllStationByCountryId(countryId: String): List<StationEntity>
}