package com.razibkani.footballclubfinal.utils

import android.support.v4.content.ContextCompat
import android.support.v7.widget.RecyclerView
import android.view.View
import com.razibkani.footballclubfinal.R
import com.razibkani.footballclubfinal.data.model.FootballLeague
import kotlinx.android.synthetic.main.item_league.view.*
import org.jetbrains.anko.backgroundDrawable

class LeagueViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun bind(league: FootballLeague, onClickListener: OnItemClickListener<FootballLeague>?) {
        itemView?.apply {
            if (league.isSelected) {
                itemView.backgroundDrawable = ContextCompat.getDrawable(itemView.context, R.drawable.bg_round_selected)
            } else {
                itemView.backgroundDrawable = ContextCompat.getDrawable(itemView.context, R.drawable.bg_round_unselected)
            }
            textLeague.text = league.strLeague

            onClickListener?.let {
                setOnClickListener { onClickListener.onClick(adapterPosition, league) }
            }
        }
    }
}