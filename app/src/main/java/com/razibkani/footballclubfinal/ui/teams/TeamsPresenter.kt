package com.razibkani.footballclubfinal.ui.teams

import com.razibkani.footballclubfinal.data.DataManager
import com.razibkani.footballclubfinal.ui.base.BasePresenter
import com.razibkani.footballclubfinal.utils.CoroutineContextProvider
import kotlinx.coroutines.experimental.async
import javax.inject.Inject

class TeamsPresenter @Inject constructor(private val dataManager: DataManager,
                                         private val context: CoroutineContextProvider) : BasePresenter<TeamsMvpView>() {

    fun getTeams(leagueName: String) {
        mvpView?.showLoading()

        async(context.main) {
            val data = dataManager.getFootballTeams(leagueName)

            mvpView?.apply {
                if (data.teams.isNotEmpty()) {
                    this.updateDataTeams(data.teams)
                } else {
                    this.showErrorMessage("Team Kosong")
                }
                this.hideLoading()
            }
        }
    }

    fun getLeagues() {
        mvpView?.updateDataLeagues(dataManager.getLeagues())
    }
}