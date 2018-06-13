package com.razibkani.footballclubfinal.ui.search.searchmatches

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import com.razibkani.footballclubfinal.R
import com.razibkani.footballclubfinal.data.model.Event
import com.razibkani.footballclubfinal.utils.OnItemClickListener
import com.razibkani.footballclubfinal.utils.inflate
import javax.inject.Inject

class SearchMatchesAdapter @Inject constructor() : RecyclerView.Adapter<SearchMatchesViewHolder>() {

    private var footballEvents: List<Event> = ArrayList()
    lateinit var onItemClickListener: OnItemClickListener<Event>

    fun updateData(newFootballEvents: List<Event>) {
        footballEvents = newFootballEvents
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchMatchesViewHolder {
        val view = parent.inflate(R.layout.item_prev_match)
        return SearchMatchesViewHolder(view)
    }

    override fun getItemCount(): Int {
        return footballEvents.size
    }

    override fun onBindViewHolder(holder: SearchMatchesViewHolder, position: Int) {
        holder.bind(footballEvents[position], onItemClickListener)
    }
}