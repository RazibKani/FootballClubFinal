package com.razibkani.footballclubfinal.ui.search.searchmatches

import com.razibkani.footballclubfinal.data.model.Event
import com.razibkani.footballclubfinal.ui.base.MvpView

interface SearchMatchesMvpView : MvpView {

    fun showLoading()

    fun hideLoading()

    fun showErrorMessage(message: String)

    fun updateMatches(matches: List<Event>)
}