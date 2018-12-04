package ru.tutu.stations.localdb.room.mapper

import org.mapstruct.Mapper
import ru.tutu.stations.localdb.model.Country
import ru.tutu.stations.localdb.room.entity.CountryEntity
import ru.tutu.stations.localdb.room.mapper.base.BaseMapper

/**
 * @author Grigoriy Pryamov.
 */
@Mapper
interface CountryMapper : BaseMapper<Country, CountryEntity> {
}