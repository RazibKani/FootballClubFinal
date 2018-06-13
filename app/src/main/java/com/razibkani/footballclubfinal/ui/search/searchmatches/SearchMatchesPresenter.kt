package com.razibkani.footballclubfinal.ui.search.searchmatches

import com.razibkani.footballclubfinal.data.DataManager
import com.razibkani.footballclubfinal.ui.base.BasePresenter
import com.razibkani.footballclubfinal.utils.CoroutineContextProvider
import kotlinx.coroutines.experimental.async
import javax.inject.Inject

class SearchMatchesPresenter @Inject constructor(private val dataManager: DataManager,
                                                 private val context: CoroutineContextProvider) : BasePresenter<SearchMatchesMvpView>() {

    fun getEventByKeyword(keyword: String) {
        mvpView?.showLoading()

        async(context.main) {
            val data = dataManager.getEventByKeyword(keyword)

            mvpView?.apply {
                if (data.events.isNotEmpty()) {
                    this.updateMatches(data.events)
                } else {
                    this.showErrorMessage("Tidak ada hasil")
                }
                this.hideLoading()
            }
        }
    }
}