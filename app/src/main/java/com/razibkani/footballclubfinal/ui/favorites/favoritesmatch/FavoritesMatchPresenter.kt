package com.razibkani.footballclubfinal.ui.favorites.favoritesmatch

import com.razibkani.footballclubfinal.data.DataManager
import com.razibkani.footballclubfinal.ui.base.BasePresenter
import javax.inject.Inject

class FavoritesMatchPresenter @Inject constructor(private val dataManager: DataManager) : BasePresenter<FavoritesMatchMvpView>() {

    fun getFavoriteEvents() {
        mvpView?.apply {
            showLoading()
            updateData(dataManager.getFavoritesEvent())
            hideLoading()
        }
    }
}