package com.razibkani.footballclubfinal.ui.search.searchteam

import com.razibkani.footballclubfinal.data.DataManager
import com.razibkani.footballclubfinal.ui.base.BasePresenter
import com.razibkani.footballclubfinal.utils.CoroutineContextProvider
import kotlinx.coroutines.experimental.async
import javax.inject.Inject

class SearchTeamPresenter @Inject constructor(private val dataManager: DataManager,
                                                 private val context: CoroutineContextProvider) : BasePresenter<SearchTeamMvpView>() {

    fun getTeamByName(teamName: String) {
        mvpView?.showLoading()

        async(context.main) {
            val data = dataManager.getTeamByName(teamName)

            mvpView?.apply {
                if (data.teams.isNotEmpty()) {
                    this.updateTeams(data.teams)
                } else {
                    this.showErrorMessage("Tidak ada hasil")
                }
                this.hideLoading()
            }
        }
    }
}