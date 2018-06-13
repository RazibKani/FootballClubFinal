package com.razibkani.footballclubfinal.ui.search.searchmatches

import android.support.v7.widget.RecyclerView
import android.view.View
import com.razibkani.footballclubfinal.data.model.Event
import com.razibkani.footballclubfinal.utils.OnItemClickListener
import com.razibkani.footballclubfinal.utils.setFormattedDate
import kotlinx.android.synthetic.main.item_prev_match.view.*

class SearchMatchesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun bind(event: Event, onClickListener: OnItemClickListener<Event>?) {
        itemView?.apply {
            textEventDate.setFormattedDate(event.dateEvent)
            textEventTime.text = event.timeEvent?.subSequence(0, 5)
            textHomeTeam.text = event.strHomeTeam
            textScoreHome.text = event.intHomeScore
            textAwayTeam.text = event.strAwayTeam
            textScoreAway.text = event.intAwayScore

            onClickListener?.let {
                setOnClickListener { onClickListener.onClick(adapterPosition, event) }
            }
        }
    }
}