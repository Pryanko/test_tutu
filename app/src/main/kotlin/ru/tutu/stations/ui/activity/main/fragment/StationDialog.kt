package ru.tutu.stations.ui.activity.main.fragment

import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import ru.tutu.stations.R
import ru.tutu.stations.ui.activity.main.adapter.model.FatStation


/**
 * @author Grigoriy Pryamov.
 */
class StationDialog : DialogFragment() {

    private lateinit var cityTitle: TextView
    private lateinit var stationTitle: TextView
    private lateinit var districtTitle: TextView
    private lateinit var regionTitle: TextView
    private lateinit var longitude: TextView
    private lateinit var latitude: TextView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.dialog_station, container, false)
        cityTitle = view.findViewById(R.id.cityTitle)
        stationTitle = view.findViewById(R.id.stationTitle)
        districtTitle = view.findViewById(R.id.districtTitle)
        regionTitle = view.findViewById(R.id.regionTitle)
        longitude = view.findViewById(R.id.longitude)
        latitude = view.findViewById(R.id.latitude)
        bind(arguments?.getParcelable(FAT_STATION_KEY)!!)
        return view
    }

    private fun bind(fatStation: FatStation?) {
        if (fatStation != null) {
            cityTitle.text = fatStation.cityTitle
            stationTitle.text = fatStation.stationTitle
            districtTitle.text = fatStation.districtTitle
            regionTitle.text = fatStation.regionTitle
            longitude.text = fatStation.longitude.toString()
            latitude.text = fatStation.latitude.toString()
        }
    }


    companion object {
        private const val FAT_STATION_KEY = "FAT_STATION"
        fun start(fatStation: FatStation): StationDialog {
            val stationDialog = StationDialog()
            val args = Bundle()
            args.putParcelable(FAT_STATION_KEY, fatStation)
            stationDialog.arguments = args
            return stationDialog
        }
    }
}