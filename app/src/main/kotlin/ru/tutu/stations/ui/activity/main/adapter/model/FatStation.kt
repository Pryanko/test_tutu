package ru.tutu.stations.ui.activity.main.adapter.model

import android.os.Parcel
import android.os.Parcelable

/**
 * @author Grigoriy Pryamov.
 */
class FatStation() : Parcelable {
    /**
     * Долгота
     */
    var longitude: Double? = 0.0
    /**
     * Широта
     */
    var latitude: Double? = 0.0
    /**
     * Наименование города
     */
    var cityTitle = ""
    /**
     * Описание наименования
     */
    var districtTitle = ""
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

    constructor(parcel: Parcel) : this() {
        longitude = parcel.readValue(Double::class.java.classLoader) as? Double
        latitude = parcel.readValue(Double::class.java.classLoader) as? Double
        cityTitle = parcel.readString()
        districtTitle = parcel.readString()
        regionTitle = parcel.readString()
        stationId = parcel.readLong()
        stationTitle = parcel.readString()
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeValue(longitude)
        parcel.writeValue(latitude)
        parcel.writeString(cityTitle)
        parcel.writeString(districtTitle)
        parcel.writeString(regionTitle)
        parcel.writeLong(stationId)
        parcel.writeString(stationTitle)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<FatStation> {
        override fun createFromParcel(parcel: Parcel): FatStation {
            return FatStation(parcel)
        }

        override fun newArray(size: Int): Array<FatStation?> {
            return arrayOfNulls(size)
        }
    }

}