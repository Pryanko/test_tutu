package ru.tutu.stations.localdb.impl

import ru.tutu.stations.localdb.AppDatabase
import ru.tutu.stations.localdb.impl.base.ModelRepositoryWithLongId
import ru.tutu.stations.localdb.model.Country
import ru.tutu.stations.localdb.repository.CountryRepository
import ru.tutu.stations.localdb.room.entity.CountryEntity
import ru.tutu.stations.localdb.room.mapper.CountryMapper
import javax.inject.Inject
import javax.inject.Singleton

/**
 * @author Grigoriy Pryamov.
 */
@Singleton
class CountryRepositoryImpl @Inject constructor(appDatabase: AppDatabase, countryMapper: CountryMapper) :
    ModelRepositoryWithLongId<Country, CountryEntity>(appDatabase), CountryRepository {

    override val dao = appDatabase.countryDao()
    override val mapper = countryMapper

    override fun getAllCountries(): List<Country> = mapper.entityListToModelList(dao.getAllCountries())!!

    override fun containsCountry(nameToLower: String): Boolean {
        return dao.containsCountry(nameToLower) != null
    }
}