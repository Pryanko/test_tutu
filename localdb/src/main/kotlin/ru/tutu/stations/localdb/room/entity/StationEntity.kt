package ru.tutu.stations.localdb.room.entity

import android.arch.persistence.room.Entity
import android.arch.persistence.room.ForeignKey
import android.arch.persistence.room.PrimaryKey
import ru.tutu.stations.localdb.room.entity.base.EntityWithId

/**
 * @author Grigoriy Pryamov.
 */
@Entity(
    tableName = "Station",
    foreignKeys = [
        ForeignKey(
            entity = CountryEntity::class,
            parentColumns = ["id"],
            childColumns = ["countryId"],
            onDelete = ForeignKey.CASCADE)
    ])
class StationEntity : EntityWithId<Long> {
    /**
     * Id station
     */
    @PrimaryKey(autoGenerate = true)
    override var id = 0L
    /**
     * Id региона
     */
    var countryId = 0L
    /**
     * Долгота
     */
    var longitude = 0.0
    /**
     * Широта
     */
    var latitude = 0.0
    /**
     * Описание наименования
     */
    var districtTitle = ""
    /**
     * Наименование города
     */
    var cityTitle = ""
    /**
     * Наименование региона
     */
    var regionTitle = ""
    /**
     * Id станции
     */
    var stationId = 0L
    /**
     * Наименование станции
     */
    var stationTitle = ""
}