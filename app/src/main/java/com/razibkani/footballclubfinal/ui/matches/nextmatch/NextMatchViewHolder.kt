package com.razibkani.footballclubfinal.ui.matches.nextmatch

import android.support.v7.widget.RecyclerView
import android.view.View
import com.razibkani.footballclubfinal.data.model.Event
import com.razibkani.footballclubfinal.utils.OnItemClickListener
import com.razibkani.footballclubfinal.utils.setFormattedDate
import kotlinx.android.synthetic.main.item_prev_match.view.*

class NextMatchViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun bind(event: Event, onClickListener: OnItemClickListener<Event>?) {
        itemView.textEventDate.setFormattedDate(event.dateEvent)
        itemView.textHomeTeam.text = event.strHomeTeam
        itemView.textAwayTeam.text = event.strAwayTeam

        onClickListener?.let {
            itemView.setOnClickListener { onClickListener.onClick(event) }
        }
    }
}