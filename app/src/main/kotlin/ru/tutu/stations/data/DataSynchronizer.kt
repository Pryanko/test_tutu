package ru.tutu.stations.data

import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single
import ru.digipeople.logger.LoggerFactory
import ru.tutu.stations.api.mapper.CountryMapper
import ru.tutu.stations.api.mapper.StationMapper
import ru.tutu.stations.data.apppreferences.AppPrefs
import ru.tutu.stations.data.base.BaseSynchronizer
import ru.tutu.stations.localdb.base.DbTransaction
import ru.tutu.stations.localdb.repository.CountryRepository
import ru.tutu.stations.localdb.repository.StationRepository
import ru.tutu.stations.network.ApiWorker
import ru.tutu.stations.network.answer.Answer
import ru.tutu.stations.network.answer.City
import ru.tutu.stations.network.model.Response
import java.util.concurrent.TimeUnit
import javax.inject.Inject
import javax.inject.Singleton

/**
 * @author Grigoriy Pryamov.
 */
@Singleton
class DataSynchronizer @Inject constructor(
    private val apiWorker: ApiWorker,
    private val countryRepository: CountryRepository,
    private val stationRepository: StationRepository,
    private val appPrefs: AppPrefs,
    transaction: DbTransaction
) : BaseSynchronizer<City, Answer<City>>(transaction) {

    private val logger = LoggerFactory.getLogger(DataSynchronizer::class)
    private val countryMapper = CountryMapper.INSTANCE()
    private val stationMapper = StationMapper.INSTANCE()

    override fun apiSource(): Single<Response<Answer<City>>> {
        return apiWorker.allStations()
    }

    fun syncStatusChanges(): Observable<SyncStatus> = statusSubject

    fun dataSync(): Completable {
        logger.trace("appDataSync: create")
        return if (isSyncRequired()) {
            sync()
                .doOnComplete {
                    updateSynchronizationTime(System.currentTimeMillis())
                    statusSubject.onNext(SuccessStatus)
                }
        } else {
            statusSubject.onNext(NotRequireStatus)
            Completable.complete()
        }
    }

    override fun store(entities: List<City>) {
        countryRepository.deleteAllCascade()
        var countryId = 0L
        entities.forEach { country ->
            val tempLowerCase = country.countryTitle.toLowerCase()
            if (!countryRepository.containsCountry(tempLowerCase)) {
                val tempCountry = countryMapper.entityToModel(country)
                tempCountry.countryTitleToLower = tempLowerCase
                countryRepository.insert(tempCountry)
                countryId = tempCountry.id
//                logger.trace("Country create: $tempLowerCase")
            }
            storeStation(country.stations, countryId)
        }
    }

    private fun storeStation(entities: List<City>, countryId: Long) {
        entities.forEach { station ->
            val tempStation = stationMapper.entityToModel(station)
            tempStation.countryId = countryId
            stationRepository.insert(tempStation)
        }
    }

    private fun isSyncRequired(): Boolean {
        return System.currentTimeMillis() - getSynchronizationTime() > APP_DATA_ACTUALITY_TIME
    }

    private fun updateSynchronizationTime(time: Long) {
        appPrefs.dataSyncTime = time
    }

    private fun getSynchronizationTime(): Long = appPrefs.dataSyncTime

    companion object {
        /**
         * Срок актуальности полученных данных
         */
        private val APP_DATA_ACTUALITY_TIME = TimeUnit.MINUTES.toMillis(1) //12 Часов
    }

    override fun syncEnable(): Boolean = countryRepository.isContainsData()
}