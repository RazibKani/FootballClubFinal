package com.razibkani.footballclubfinal.ui.favorites.favoritesmatch

import android.support.v7.widget.RecyclerView
import android.view.View
import com.razibkani.footballclubfinal.data.model.Event
import com.razibkani.footballclubfinal.utils.OnItemClickListener
import com.razibkani.footballclubfinal.utils.hide
import com.razibkani.footballclubfinal.utils.setFormattedDate
import kotlinx.android.synthetic.main.item_prev_match.view.*

class FavoritesMatchViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun bind(favoriteEvent: Event, onClickListener: OnItemClickListener<Event>?) {
        itemView.textEventDate.setFormattedDate(favoriteEvent.dateEvent)
        itemView.textEventTime.text = favoriteEvent.timeEvent?.subSequence(0, 5)
        itemView.textHomeTeam.text = favoriteEvent.strHomeTeam
        itemView.textAwayTeam.text = favoriteEvent.strAwayTeam
        if (favoriteEvent.intAwayScore == null) {
            itemView.textScoreHome.hide()
        } else {
            itemView.textScoreHome.text = favoriteEvent.intHomeScore.toString()
        }
        if (favoriteEvent.intAwayScore == null) {
            itemView.textScoreAway.hide()
        } else {
            itemView.textScoreAway.text = favoriteEvent.intAwayScore.toString()
        }

        onClickListener?.let {
            itemView.setOnClickListener { onClickListener.onClick(adapterPosition, favoriteEvent) }
        }
    }
}