package com.razibkani.footballclubfinal.ui.matches.nextmatch

import com.razibkani.footballclubfinal.data.model.Event
import com.razibkani.footballclubfinal.data.model.FootballLeague
import com.razibkani.footballclubfinal.ui.base.MvpView

interface NextMatchMvpView : MvpView {

    fun showLoading()

    fun hideLoading()

    fun showEmptyState()

    fun updateDataEvents(eventList: List<Event>)

    fun updateDataLeagues(leagues: List<FootballLeague>)
}