package com.razibkani.footballclubfinal.ui.teams

import com.razibkani.footballclubfinal.data.model.FootballLeague
import com.razibkani.footballclubfinal.data.model.FootballTeam
import com.razibkani.footballclubfinal.ui.base.MvpView

interface TeamsMvpView : MvpView {

    fun showLoading()

    fun hideLoading()

    fun showErrorMessage(message: String)

    fun updateDataTeams(teamList: List<FootballTeam>)

    fun updateDataLeagues(leagues: List<FootballLeague>)
}