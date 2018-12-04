package ru.tutu.stations.localdb.impl

import ru.tutu.stations.localdb.AppDatabase
import ru.tutu.stations.localdb.impl.base.ModelRepositoryWithLongId
import ru.tutu.stations.localdb.model.Station
import ru.tutu.stations.localdb.repository.StationRepository
import ru.tutu.stations.localdb.room.entity.StationEntity
import ru.tutu.stations.localdb.room.mapper.StationMapper
import javax.inject.Inject
import javax.inject.Singleton

/**
 * @author Grigoriy Pryamov.
 */
@Singleton
class StationRepositoryImpl @Inject constructor(appDatabase: AppDatabase, stationMapper: StationMapper) :
    ModelRepositoryWithLongId<Station, StationEntity>(appDatabase), StationRepository {

    override val dao = appDatabase.stationDao()
    override val mapper = stationMapper

    override fun getAllStationByCountryId(countryId: String): List<Station> {
        return mapper.entityListToModelList(dao.getAllStationByCountryId(countryId))!!
    }

}