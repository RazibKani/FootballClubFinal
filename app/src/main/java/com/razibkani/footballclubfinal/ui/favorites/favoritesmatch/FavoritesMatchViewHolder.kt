package com.razibkani.footballclubfinal.ui.favorites.favoritesmatch

import android.support.v7.widget.RecyclerView
import android.view.View
import com.razibkani.footballclubfinal.data.model.FavoriteEvent
import com.razibkani.footballclubfinal.utils.OnItemClickListener
import com.razibkani.footballclubfinal.utils.hide
import com.razibkani.footballclubfinal.utils.setFormattedDate
import kotlinx.android.synthetic.main.item_fav_match.view.*

class FavoritesMatchViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun bind(favoriteEvent: FavoriteEvent, onClickListener: OnItemClickListener<FavoriteEvent>?) {
        itemView.textEventDate.setFormattedDate(favoriteEvent.dateEvent)
        itemView.textHomeTeam.text = favoriteEvent.homeTeam
        itemView.textAwayTeam.text = favoriteEvent.awayTeam
        if (favoriteEvent.awayScore == null) {
            itemView.textScoreHome.hide()
        } else {
            itemView.textScoreHome.text = favoriteEvent.homeScore.toString()
        }
        if (favoriteEvent.awayScore == null) {
            itemView.textScoreAway.hide()
        } else {
            itemView.textScoreAway.text = favoriteEvent.awayScore.toString()
        }

        onClickListener?.let {
            itemView.setOnClickListener { onClickListener.onClick(favoriteEvent) }
        }
    }
}