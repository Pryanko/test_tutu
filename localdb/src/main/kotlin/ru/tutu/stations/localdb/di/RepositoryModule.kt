package ru.tutu.stations.localdb.di

import dagger.Binds
import dagger.Module
import ru.tutu.stations.localdb.impl.CountryRepositoryImpl
import ru.tutu.stations.localdb.repository.CountryRepository

/**
 * @author Grigoriy Pryamov.
 */
@Module
abstract class RepositoryModule {
    @Binds
    internal abstract fun countryRepository(countryRepository: CountryRepositoryImpl): CountryRepository
}