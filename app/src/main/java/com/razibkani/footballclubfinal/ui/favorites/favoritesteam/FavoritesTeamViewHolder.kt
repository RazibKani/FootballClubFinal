package com.razibkani.footballclubfinal.ui.favorites.favoritesteam

import android.support.v7.widget.RecyclerView
import android.view.View
import com.razibkani.footballclubfinal.data.model.FootballTeam
import com.razibkani.footballclubfinal.utils.OnItemClickListener
import com.razibkani.footballclubfinal.utils.loadUrl
import kotlinx.android.synthetic.main.item_team.view.*

class FavoritesTeamViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

    fun bind(team: FootballTeam, onClickListener: OnItemClickListener<FootballTeam>?) {
        itemView?.apply {
            itemView.imageTeam.loadUrl(team.strTeamBadge)
            itemView.textTeamName.text = team.strTeam

            onClickListener?.let {
                setOnClickListener { onClickListener.onClick(adapterPosition, team) }
            }
        }
    }
}