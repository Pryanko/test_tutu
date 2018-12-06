package ru.tutu.stations.api.mapper

import org.mapstruct.Mapper
import ru.tutu.stations.localdb.model.Point

/**
 * @author Grigoriy Pryamov.
 */
@Mapper
interface PointMapper {

    fun entityToModel(entity: ru.tutu.stations.network.answer.Point): Point
}