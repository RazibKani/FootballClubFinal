package com.razibkani.footballclubfinal.ui.favorites.favoritesmatch

import com.razibkani.footballclubfinal.data.model.Event
import com.razibkani.footballclubfinal.ui.base.MvpView

interface FavoritesMatchMvpView : MvpView {

    fun showLoading()

    fun hideLoading()

    fun updateData(favoriteEvents: List<Event>)

}