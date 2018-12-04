package ru.tutu.stations.data

import io.reactivex.Single
import ru.digipeople.logger.LoggerFactory
import ru.tutu.stations.api.mapper.CountryMapper
import ru.tutu.stations.data.base.BaseSynchronizer
import ru.tutu.stations.localdb.base.DbTransaction
import ru.tutu.stations.localdb.repository.CountryRepository
import ru.tutu.stations.network.ApiWorker
import ru.tutu.stations.network.answer.Answer
import ru.tutu.stations.network.answer.City
import ru.tutu.stations.network.model.Response
import javax.inject.Inject
import javax.inject.Singleton

/**
 * @author Grigoriy Pryamov.
 */
@Singleton
class DataSynchronizer @Inject constructor(
    private val apiWorker: ApiWorker,
    private val repository: CountryRepository,
    transaction: DbTransaction
) : BaseSynchronizer<City, Answer<City>>(transaction) {

    private val logger = LoggerFactory.getLogger(DataSynchronizer::class)
    private val countryMapper = CountryMapper.INSTANCE()

    override fun apiSource(): Single<Response<Answer<City>>> {
        return apiWorker.allStations()
    }

    override fun store(entities: List<City>) {
        entities.forEach { country ->
            val tempLowerCase = country.countryTitle.toLowerCase()
            if (!repository.containsCountry(tempLowerCase)) {
                val tempCountry = countryMapper.entityToModel(country)
                tempCountry.countryTitleToLower = tempLowerCase
                repository.insert(tempCountry)
                logger.trace("Country create: $tempLowerCase")
            }

        }
    }
}