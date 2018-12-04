package ru.tutu.stations.localdb.di

import dagger.Module
import dagger.Provides
import dagger.Reusable
import ru.tutu.stations.localdb.room.mapper.CountryMapper
import ru.tutu.stations.localdb.room.mapper.CountryMapperImpl

/**
 * @author Grigoriy Pryamov.
 */
@Module
class MapperModule {

    @Provides
    @Reusable
    internal fun countryMapper(): CountryMapper = CountryMapperImpl()

}