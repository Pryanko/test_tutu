package ru.tutu.stations.ui.activity.main.interactor

import io.reactivex.Single
import ru.digipeople.logger.LoggerFactory
import ru.tutu.stations.localdb.model.Country
import ru.tutu.stations.localdb.model.Station
import ru.tutu.stations.localdb.repository.CountryRepository
import ru.tutu.stations.localdb.repository.StationRepository
import ru.tutu.stations.ui.activity.main.adapter.model.FatStation
import ru.tutu.stations.ui.activity.main.adapter.model.ShortCountry
import ru.tutu.stations.ui.activity.main.adapter.model.ShortStation
import javax.inject.Inject

/**
 * Загрузчик регионов и станций
 *
 * @author Grigoriy Pryamov.
 */
class CountryStationLoader @Inject constructor(
    private val countryRepository: CountryRepository,
    private val stationRepository: StationRepository
) {
    private val logger = LoggerFactory.getLogger(CountryStationLoader::class)

    fun load(): Single<List<ShortCountry>> {
        logger.trace("load")
        return Single.fromCallable {
            val shortCountries = ArrayList<ShortCountry>()
            val counties = countryRepository.getAllCountries()
            // Сборку данных делегериуем Kotlin уровню
            counties.forEach { country ->
                val shortCountry = mapToShortCountry(country)
                val stations = stationRepository.getAllStationByCountryId(country.id)
                stations.forEach { station ->
                    shortCountry.stations.add(mapToShortStation(station))
                }
                shortCountries.add(shortCountry)
            }
            return@fromCallable shortCountries
        }
    }

    fun stationLoad(stationId: Long): Single<FatStation> {
        return Single.fromCallable {
            return@fromCallable mapToFat(stationRepository.getById(stationId)!!)
        }
    }

    private fun mapToShortCountry(country: Country): ShortCountry {
        val shortCountry = ShortCountry()
        shortCountry.id = country.id
        shortCountry.countryTitle = country.countryTitle
        shortCountry.districtTitle = country.districtTitle
        return shortCountry
    }

    private fun mapToShortStation(station: Station): ShortStation {
        val shortStation = ShortStation()
        shortStation.id = station.id
        shortStation.regionTitle = station.cityTitle
        shortStation.stationTitle = station.stationTitle
        return shortStation
    }

    private fun mapToFat(station: Station): FatStation {
        val fatStation = FatStation()
        fatStation.stationId = station.stationId
        fatStation.cityTitle = station.cityTitle
        fatStation.districtTitle = station.districtTitle
        fatStation.latitude = station.point.latitude
        fatStation.longitude = station.point.longitude
        fatStation.regionTitle = station.regionTitle
        fatStation.stationTitle = station.stationTitle
        return fatStation
    }
}