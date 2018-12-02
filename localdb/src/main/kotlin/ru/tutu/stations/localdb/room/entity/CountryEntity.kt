package ru.tutu.stations.localdb.room.entity

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import ru.tutu.stations.localdb.room.entity.base.EntityWithId

/**
 * Регион БД Сущность
 *
 * @author Grigoriy Pryamov.
 */
@Entity(tableName = "Country")
class CountryEntity : EntityWithId<Long> {
    /**
     * Id
     */
    @PrimaryKey(autoGenerate = true)
    override var id: Long = 0
    /**
     * Наименование региона - страны
     */
    var countryTitle = ""
    /**
     * Наименование региона - страны (в нижнем реестре)
     */
    var countryTitleToLower = ""
    /**
     * Описание
     */
    var districtTitle = ""
}