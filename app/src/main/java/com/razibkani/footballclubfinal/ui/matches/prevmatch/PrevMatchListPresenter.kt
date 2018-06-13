package com.razibkani.footballclubfinal.ui.matches.prevmatch

import com.razibkani.footballclubfinal.data.DataManager
import com.razibkani.footballclubfinal.ui.base.BasePresenter
import com.razibkani.footballclubfinal.utils.CoroutineContextProvider
import kotlinx.coroutines.experimental.async
import javax.inject.Inject

class PrevMatchListPresenter @Inject constructor(private val dataManager: DataManager,
                                                 private val context: CoroutineContextProvider) : BasePresenter<PrevMatchListMvpView>() {

    fun getEvents(leagueId: String) {
        mvpView?.showLoading()

        async(context.main) {
            val data = dataManager.getFootballPrevEvent(leagueId)

            mvpView?.apply {
                if (data.events.isNotEmpty()) {
                    this.updateDataEvents(data.events)
                } else {
                    this.showErrorMessage("Event Kosong")
                }
                this.hideLoading()
            }
        }
    }

    fun getLeagues() {
        mvpView?.updateDataLeagues(dataManager.getLeagues())
    }
}