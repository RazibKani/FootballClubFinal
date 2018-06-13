package com.razibkani.footballclubfinal.ui.detailteam

import com.razibkani.footballclubfinal.data.DataManager
import com.razibkani.footballclubfinal.data.local.DbCallback
import com.razibkani.footballclubfinal.data.model.FootballTeam
import com.razibkani.footballclubfinal.ui.base.BasePresenter
import javax.inject.Inject

class DetailTeamPresenter @Inject constructor(private val dataManager: DataManager) : BasePresenter<DetailTeamMvpView>() {

    fun favoriteState(teamId: String): Boolean = dataManager.favoriteTeamState(teamId)

    fun removeFromFavorite(teamId: String) {
        dataManager.removeTeamFromFavorite(teamId, object : DbCallback {
            override fun onSuccess() {
                mvpView?.showSuccessMessage("Removed from Favorite")
            }

            override fun onFailed() {
                mvpView?.showErrorMessage("Failed Remove from Favorite")
            }
        })
    }

    fun addToFavorite(team: FootballTeam) {
        dataManager.addTeamToFavorite(team, object : DbCallback {
            override fun onSuccess() {
                mvpView?.showSuccessMessage("Added to Favorite")
            }

            override fun onFailed() {
                mvpView?.showErrorMessage("Failed Add to Favorite")
            }
        })
    }
}