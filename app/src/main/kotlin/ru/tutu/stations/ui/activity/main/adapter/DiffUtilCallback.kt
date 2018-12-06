package ru.tutu.stations.ui.activity.main.adapter

import ru.tutu.stations.ui.activity.main.adapter.model.ShortCountry
import ru.tutu.stations.ui.activity.main.adapter.model.ShortStation
import ru.tutu.stations.ui.adapter.BaseDiffUtilCallback

/**
 * @author Grigoriy Pryamov.
 */
class DiffUtilCallback(oldItems: List<Any>, newItems: List<Any>) : BaseDiffUtilCallback<Any>(oldItems, newItems) {

    override fun areItemsTheSame(oldItem: Any, newItem: Any): Boolean {
        if (oldItem::class != newItem::class) return false
        return when (oldItem) {
            is ShortStation -> oldItem.id == (newItem as ShortStation).id
            is ShortCountry -> oldItem.id == (newItem as ShortCountry).id
            else -> throw IllegalArgumentException("Unknown item type = " + oldItem::class)
        }
    }

    override fun areContentsTheSame(oldItem: Any?, newItem: Any?): Boolean {
        return oldItem == newItem
    }
}