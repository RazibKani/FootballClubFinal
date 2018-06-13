package com.razibkani.footballclubfinal.utils

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import com.razibkani.footballclubfinal.R
import com.razibkani.footballclubfinal.data.model.FootballLeague
import javax.inject.Inject

class LeagueAdapter @Inject constructor() : RecyclerView.Adapter<LeagueViewHolder>() {

    private var footballLeagues: MutableList<FootballLeague> = mutableListOf()
    lateinit var onItemClickListener: OnItemClickListener<FootballLeague>

    fun updateData(newFootballLeague: List<FootballLeague>) {
        footballLeagues = newFootballLeague.toMutableList()
        notifyDataSetChanged()
    }

    fun getLeague(position: Int): FootballLeague {
        return footballLeagues[position]
    }

    fun selectLeague(position: Int) {
        for (league in footballLeagues) {
            league.isSelected = false
        }

        footballLeagues[position].isSelected = true
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LeagueViewHolder {
        val view = parent.inflate(R.layout.item_league)
        return LeagueViewHolder(view)
    }

    override fun getItemCount(): Int {
        return footballLeagues.size
    }

    override fun onBindViewHolder(holder: LeagueViewHolder, position: Int) {
        holder.bind(footballLeagues[position], onItemClickListener)
    }
}