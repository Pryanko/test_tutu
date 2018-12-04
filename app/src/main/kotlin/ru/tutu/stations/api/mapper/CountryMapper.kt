package ru.tutu.stations.api.mapper

import org.mapstruct.Mapper
import org.mapstruct.ReportingPolicy
import org.mapstruct.factory.Mappers
import ru.tutu.stations.localdb.model.Country
import ru.tutu.stations.network.answer.City

/**
 * @author Grigoriy Pryamov.
 */
@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
interface CountryMapper {

    fun entityToModel(entity: City): Country

    companion object {
        fun INSTANCE() = Mappers.getMapper(CountryMapper::class.java)
    }
}