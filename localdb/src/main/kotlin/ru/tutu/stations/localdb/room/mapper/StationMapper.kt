package ru.tutu.stations.localdb.room.mapper

import org.mapstruct.Mapper
import org.mapstruct.Mapping
import org.mapstruct.Mappings
import ru.tutu.stations.localdb.model.Station
import ru.tutu.stations.localdb.room.entity.StationEntity
import ru.tutu.stations.localdb.room.mapper.base.BaseMapper

/**
 * @author Grigoriy Pryamov.
 */
@Mapper
interface StationMapper : BaseMapper<Station, StationEntity> {

    @Mappings(
        Mapping(target = "point.longitude", source = "longitude"),
        Mapping(target = "point.latitude", source = "latitude")
    )
    override fun entityToModel(entity: StationEntity?): Station?

    @Mappings(
        Mapping(target = "longitude", source = "point.longitude"),
        Mapping(target = "latitude", source = "point.latitude"),
        Mapping(target = "id", ignore = true)
    )
    override fun modelToEntity(model: Station?): StationEntity?
}