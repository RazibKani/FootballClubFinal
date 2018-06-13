package com.razibkani.footballclubfinal.ui.favorites.favoritesteam

import com.razibkani.footballclubfinal.data.DataManager
import com.razibkani.footballclubfinal.ui.base.BasePresenter
import javax.inject.Inject

class FavoritesTeamPresenter @Inject constructor(private val dataManager: DataManager) : BasePresenter<FavoritesTeamMvpView>() {

    fun getFavoritesTeam() {
        mvpView?.apply {
            showLoading()
            updateData(dataManager.getFavoritesTeam())
            hideLoading()
        }
    }
}