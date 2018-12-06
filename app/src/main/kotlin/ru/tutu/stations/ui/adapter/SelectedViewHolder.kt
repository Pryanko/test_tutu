package ru.tutu.stations.ui.adapter

import android.support.v7.widget.RecyclerView
import android.view.View

/**
 * Базовый меню вью холдер, для передачи selected состояния
 *
 * @author Grigoriy Pryamov
 */
abstract class SelectedViewHolder<M>(itemView: View) : RecyclerView.ViewHolder(itemView) {

    abstract fun applySelected(enable: Boolean)

    abstract fun bindAll(m: M)
}