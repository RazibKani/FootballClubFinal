package com.razibkani.footballclubfinal.ui.search.searchteam

import com.razibkani.footballclubfinal.data.model.FootballTeam
import com.razibkani.footballclubfinal.ui.base.MvpView

interface SearchTeamMvpView : MvpView {

    fun showLoading()

    fun hideLoading()

    fun showErrorMessage(message: String)

    fun updateTeams(teams: List<FootballTeam>)
}