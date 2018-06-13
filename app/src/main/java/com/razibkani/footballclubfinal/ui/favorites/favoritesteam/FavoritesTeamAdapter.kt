package com.razibkani.footballclubfinal.ui.favorites.favoritesteam

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import com.razibkani.footballclubfinal.R
import com.razibkani.footballclubfinal.data.model.FootballTeam
import com.razibkani.footballclubfinal.utils.OnItemClickListener
import com.razibkani.footballclubfinal.utils.inflate
import javax.inject.Inject

class FavoritesTeamAdapter @Inject constructor() : RecyclerView.Adapter<FavoritesTeamViewHolder>() {

    private var footballTeams: List<FootballTeam> = ArrayList()
    lateinit var onItemClickListener: OnItemClickListener<FootballTeam>

    fun updateData(newFootballTeams: List<FootballTeam>) {
        footballTeams = newFootballTeams
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoritesTeamViewHolder {
        val view = parent.inflate(R.layout.item_team)
        return FavoritesTeamViewHolder(view)
    }

    override fun getItemCount(): Int {
        return footballTeams.size
    }

    override fun onBindViewHolder(holder: FavoritesTeamViewHolder, position: Int) {
        holder.bind(footballTeams[position], onItemClickListener)
    }

}