package com.razibkani.footballclubfinal.ui.matches.nextmatch

import com.razibkani.footballclubfinal.data.DataManager
import com.razibkani.footballclubfinal.ui.base.BasePresenter
import com.razibkani.footballclubfinal.utils.CoroutineContextProvider
import kotlinx.coroutines.experimental.async
import javax.inject.Inject

class NextMatchPresenter @Inject constructor(private val dataManager: DataManager,
                                             private val context: CoroutineContextProvider) : BasePresenter<NextMatchMvpView>() {

    fun getEvents(leagueId: String) {
        mvpView?.showLoading()

        async(context.main) {
            val data = dataManager.getFootballNextEvent(leagueId)

            mvpView?.apply {
                if (data.events != null && data.events.isNotEmpty()) {
                    this.updateDataEvents(data.events)
                } else {
                    this.showEmptyState()
                }
                this.hideLoading()
            }
        }
    }

    fun getLeagues() {
        mvpView?.updateDataLeagues(dataManager.getLeagues())
    }
}