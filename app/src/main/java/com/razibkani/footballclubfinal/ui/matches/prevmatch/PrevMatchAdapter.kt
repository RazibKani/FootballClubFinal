package com.razibkani.footballclubfinal.ui.matches.prevmatch

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import com.razibkani.footballclubfinal.R
import com.razibkani.footballclubfinal.data.model.Event
import com.razibkani.footballclubfinal.utils.OnItemClickListener
import com.razibkani.footballclubfinal.utils.inflate
import javax.inject.Inject

class PrevMatchAdapter @Inject constructor() : RecyclerView.Adapter<PrevMatchViewHolder>() {

    private var footballEvents: List<Event> = ArrayList()
    lateinit var onItemClickListener: OnItemClickListener<Event>

    fun updateData(newFootballEvents: List<Event>) {
        footballEvents = newFootballEvents
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PrevMatchViewHolder {
        val view = parent.inflate(R.layout.item_prev_match)
        return PrevMatchViewHolder(view)
    }

    override fun getItemCount(): Int {
        return footballEvents.size
    }

    override fun onBindViewHolder(holder: PrevMatchViewHolder, position: Int) {
        holder.bind(footballEvents[position], onItemClickListener)
    }
}