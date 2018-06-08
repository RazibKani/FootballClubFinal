package com.razibkani.footballclubfinal.ui.matches.nextmatch

import com.razibkani.footballclubfinal.data.model.Event
import com.razibkani.footballclubfinal.ui.base.MvpView

interface NextMatchMvpView : MvpView {

    fun showLoading()

    fun hideLoading()

    fun showErrorMessage(message: String)

    fun updateData(data: List<Event>)
}