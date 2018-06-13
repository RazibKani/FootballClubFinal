package com.razibkani.footballclubfinal.ui.detailteam.teamplayer

import com.razibkani.footballclubfinal.data.DataManager
import com.razibkani.footballclubfinal.ui.base.BasePresenter
import com.razibkani.footballclubfinal.utils.CoroutineContextProvider
import kotlinx.coroutines.experimental.async
import javax.inject.Inject

class TeamPlayerPresenter @Inject constructor(private val dataManager: DataManager,
                                              private val context: CoroutineContextProvider) : BasePresenter<TeamPlayerMvpView>() {

    fun getPlayers(teamName: String) {
        mvpView?.showLoading()

        async(context.main) {
            val data = dataManager.getPlayerByTeamName(teamName)

            mvpView?.apply {
                if (data.player.isNotEmpty()) {
                    this.updateData(data.player)
                } else {
                    this.showErrorMessage("Player Kosong")
                }
                this.hideLoading()
            }
        }
    }
}