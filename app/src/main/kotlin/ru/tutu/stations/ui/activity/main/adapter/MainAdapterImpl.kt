package ru.tutu.stations.ui.activity.main.adapter

import android.support.v7.util.DiffUtil
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import ru.tutu.stations.R
import ru.tutu.stations.ui.activity.main.adapter.model.ShortCountry
import ru.tutu.stations.ui.activity.main.adapter.model.ShortStation
import ru.tutu.stations.ui.adapter.BaseRecyclerAdapter
import ru.tutu.stations.ui.adapter.SelectedViewHolder
import javax.inject.Inject
import javax.inject.Singleton

/**
 * @author Grigoriy Pryamov.
 */
@Singleton
class MainAdapterImpl @Inject constructor() : BaseRecyclerAdapter<RecyclerView.ViewHolder>(), MainAdapter {

    private var adapterData = AdapterData(emptyList())

    var stationClickListener: ((id: Long) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            VIEW_TYPE_COUNTRY_ITEM -> {
                val v = layoutInflater.inflate(R.layout.adapter_country_item, parent, false)
                CountryItemHolder(v)
            }
            VIEW_TYPE_STATION_ITEM -> {
                val v = layoutInflater.inflate(R.layout.adapter_station_item, parent, false)
                StationItemHolder(v)
            }
            else -> {
                throw IllegalArgumentException("Unsupported view type = $viewType")
            }
        }
    }

    override fun getItemCount(): Int {
        return adapterData.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when {
            adapterData.isCountryItem(position) -> {
                val vh = holder as CountryItemHolder
                vh.bindAll(adapterData.countryItem(position))
            }
            adapterData.isStationItem(position) -> {
                val vh = holder as StationItemHolder
                vh.bindAll(adapterData.stationItem(position))
            }
        }
    }

    override fun setCountries(countries: List<ShortCountry>) {
        val adapterData = AdapterData(countries)
        val diffUtilCallback = DiffUtilCallback(this.adapterData, adapterData)
        val difResult = DiffUtil.calculateDiff(diffUtilCallback, false)
        this.adapterData = adapterData
        difResult.dispatchUpdatesTo(this)
    }

    override fun getItemViewType(position: Int): Int {
        return when {
            adapterData.isCountryItem(position) -> VIEW_TYPE_COUNTRY_ITEM
            adapterData.isStationItem(position) -> VIEW_TYPE_STATION_ITEM
            else -> throw IllegalArgumentException("Unsupported position = $position")
        }
    }

    private inner class CountryItemHolder internal constructor(itemView: View) :
        SelectedViewHolder<ShortCountry>(itemView) {

        private var name: TextView = itemView.findViewById<View>(R.id.name) as TextView
        private var description: TextView = itemView.findViewById<View>(R.id.description) as TextView
        private var select: View = itemView.findViewById<View>(R.id.select) as View

        init {
            itemView.setOnClickListener { _ ->
                val open = adapterData.countryItem(layoutPosition).open
                adapterData.countryItem(layoutPosition).open = !open
                setCountries(adapterData.getList())
                applySelected(!open)
            }
        }

        override fun bindAll(m: ShortCountry) {
            name.text = m.countryTitle
            description.text = m.districtTitle
            applySelected(m.open)
        }

        override fun applySelected(enable: Boolean) {
            select.scaleX = if (enable) 1.0f else 0.0f
        }
    }

    private inner class StationItemHolder internal constructor(itemView: View) :
        SelectedViewHolder<ShortStation>(itemView) {

        init {
            itemView.setOnClickListener { _ -> stationClickListener?.invoke(adapterData.stationItem(layoutPosition).id) }
        }

        private var regionTitle: TextView = itemView.findViewById<View>(R.id.regionTitle) as TextView
        private var stationName: TextView = itemView.findViewById<View>(R.id.stationName) as TextView

        override fun bindAll(m: ShortStation) {
            regionTitle.text = m.regionTitle
            stationName.text = m.stationTitle
        }

        override fun applySelected(enable: Boolean) {
            //nop
        }
    }

    companion object {
        private const val VIEW_TYPE_COUNTRY_ITEM = 0
        private const val VIEW_TYPE_STATION_ITEM = 1
        /**
         * Полезная нагрузка для метода notify по умолчанию.
         * Иначе отработает стандартная анимация и все элементы "моргнут".
         */
        internal val DEFAULT_PAYLOAD = Any()
        /**
         * Полезная нагрузка для метода notify.
         * Обрабатывает изменение состояния элемента "Выбрано/Не выбрано"
         */
        internal val SELECTED_CHANGE_PAYLOAD = Any()
    }
}