package com.razibkani.footballclubfinal.ui.matches.nextmatch

import com.razibkani.footballclubfinal.data.DataManager
import com.razibkani.footballclubfinal.ui.base.BasePresenter
import com.razibkani.footballclubfinal.utils.CoroutineContextProvider
import kotlinx.coroutines.experimental.async
import javax.inject.Inject

class NextMatchPresenter @Inject constructor(private val dataManager: DataManager,
                                             private val context: CoroutineContextProvider) : BasePresenter<NextMatchMvpView>() {

    fun getEvents() {
        mvpView?.showLoading()

        async(context.main) {
            val data = dataManager.getFootballNextEvent()

            mvpView?.apply {
                if (data != null && data.events.isNotEmpty()) {
                    this.updateData(data.events)
                } else {
                    this.showErrorMessage("Event Kosong")
                }
                this.hideLoading()
            }
        }
    }
}