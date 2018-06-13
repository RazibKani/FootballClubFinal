package com.razibkani.footballclubfinal.ui.search.searchteam

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import com.razibkani.footballclubfinal.R
import com.razibkani.footballclubfinal.data.model.FootballTeam
import com.razibkani.footballclubfinal.utils.OnItemClickListener
import com.razibkani.footballclubfinal.utils.inflate
import javax.inject.Inject

class SearchTeamAdapter @Inject constructor() : RecyclerView.Adapter<SearchTeamViewHolder>() {

    private var teams: List<FootballTeam> = ArrayList()
    lateinit var onItemClickListener: OnItemClickListener<FootballTeam>

    fun updateData(newTeams: List<FootballTeam>) {
        teams = newTeams
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchTeamViewHolder {
        val view = parent.inflate(R.layout.item_team)
        return SearchTeamViewHolder(view)
    }

    override fun getItemCount(): Int {
        return teams.size
    }

    override fun onBindViewHolder(holder: SearchTeamViewHolder, position: Int) {
        holder.bind(teams[position], onItemClickListener)
    }
}