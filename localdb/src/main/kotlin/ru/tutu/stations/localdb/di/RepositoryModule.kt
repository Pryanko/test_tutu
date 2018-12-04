package ru.tutu.stations.localdb.di

import dagger.Binds
import dagger.Module
import ru.tutu.stations.localdb.impl.CountryRepositoryImpl
import ru.tutu.stations.localdb.impl.StationRepositoryImpl
import ru.tutu.stations.localdb.repository.CountryRepository
import ru.tutu.stations.localdb.repository.StationRepository

/**
 * @author Grigoriy Pryamov.
 */
@Module
abstract class RepositoryModule {
    @Binds
    internal abstract fun countryRepository(countryRepository: CountryRepositoryImpl): CountryRepository

    @Binds
    internal abstract fun stationRepository(stationRepository: StationRepositoryImpl): StationRepository
}