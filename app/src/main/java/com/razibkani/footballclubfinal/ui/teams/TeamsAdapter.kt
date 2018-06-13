package com.razibkani.footballclubfinal.ui.teams

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import com.razibkani.footballclubfinal.R
import com.razibkani.footballclubfinal.data.model.FootballTeam
import com.razibkani.footballclubfinal.utils.OnItemClickListener
import com.razibkani.footballclubfinal.utils.inflate
import javax.inject.Inject

class TeamsAdapter @Inject constructor() : RecyclerView.Adapter<TeamsViewHolder>() {

    private var teams: List<FootballTeam> = ArrayList()
    lateinit var onItemClickListener: OnItemClickListener<FootballTeam>

    fun updateData(newTeams: List<FootballTeam>) {
        teams = newTeams
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TeamsViewHolder {
        val view = parent.inflate(R.layout.item_team)
        return TeamsViewHolder(view)
    }

    override fun getItemCount(): Int {
        return teams.size
    }

    override fun onBindViewHolder(holder: TeamsViewHolder, position: Int) {
        holder.bind(teams[position], onItemClickListener)
    }
}