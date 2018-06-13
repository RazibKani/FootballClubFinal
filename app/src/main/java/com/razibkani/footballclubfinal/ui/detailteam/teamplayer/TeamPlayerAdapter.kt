package com.razibkani.footballclubfinal.ui.detailteam.teamplayer

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import com.razibkani.footballclubfinal.R
import com.razibkani.footballclubfinal.data.model.Player
import com.razibkani.footballclubfinal.utils.OnItemClickListener
import com.razibkani.footballclubfinal.utils.inflate
import javax.inject.Inject

class TeamPlayerAdapter @Inject constructor() : RecyclerView.Adapter<TeamPlayerViewHolder>() {

    private var players: List<Player> = ArrayList()
    lateinit var onItemClickListener: OnItemClickListener<Player>

    fun updateData(newPlayers: List<Player>) {
        players = newPlayers
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TeamPlayerViewHolder {
        val view = parent.inflate(R.layout.item_player)
        return TeamPlayerViewHolder(view)
    }

    override fun getItemCount(): Int {
        return players.size
    }

    override fun onBindViewHolder(holder: TeamPlayerViewHolder, position: Int) {
        holder.bind(players[position], onItemClickListener)
    }
}