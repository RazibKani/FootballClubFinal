package com.razibkani.footballclubfinal.ui.matches.prevmatch

import com.razibkani.footballclubfinal.data.model.Event
import com.razibkani.footballclubfinal.ui.base.MvpView

interface PrevMatchListMvpView : MvpView {

    fun showLoading()

    fun hideLoading()

    fun showErrorMessage(message: String)

    fun updateData(eventList: List<Event>)
}