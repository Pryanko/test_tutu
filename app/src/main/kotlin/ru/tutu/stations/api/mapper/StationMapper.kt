package ru.tutu.stations.api.mapper

import org.mapstruct.Mapper
import org.mapstruct.Mapping
import org.mapstruct.Mappings
import org.mapstruct.factory.Mappers
import ru.tutu.stations.localdb.model.Station
import ru.tutu.stations.network.answer.City

/**
 * @author Grigoriy Pryamov.
 */
@Mapper
interface StationMapper {

    @Mappings(
        Mapping(target = "id", source = "cityId"),
        Mapping(target = "countryId", ignore = true)
    )
    fun entityToModel(entity: City): Station

    companion object {
        fun INSTANCE() = Mappers.getMapper(StationMapper::class.java)
    }
}