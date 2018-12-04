package ru.tutu.stations.localdb.repository

import ru.tutu.stations.localdb.model.Station
import ru.tutu.stations.localdb.repository.base.ModelRepository

/**
 * @author Grigoriy Pryamov.
 */
interface StationRepository : ModelRepository<Station, Long> {
    fun getAllStationByCountryId(countryId: String): List<Station>
}