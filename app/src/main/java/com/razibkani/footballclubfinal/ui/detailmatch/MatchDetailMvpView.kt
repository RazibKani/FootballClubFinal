package com.razibkani.footballclubfinal.ui.detailmatch

import com.razibkani.footballclubfinal.data.model.DetailEvent
import com.razibkani.footballclubfinal.data.model.Team
import com.razibkani.footballclubfinal.ui.base.MvpView

interface MatchDetailMvpView : MvpView {

    fun showLoading()

    fun hideLoading()

    fun showSuccessMessage(successMessage: String)

    fun showErrorMessage(errorMessage: String)

    fun showData(event: DetailEvent)

    fun showHomeTeamImage(team: Team)

    fun showAwayTeamImage(team: Team)
}