package com.razibkani.footballclubfinal.ui.detailteam.teamplayer

import com.razibkani.footballclubfinal.data.model.Player
import com.razibkani.footballclubfinal.ui.base.MvpView

interface TeamPlayerMvpView : MvpView {

    fun showLoading()

    fun hideLoading()

    fun showErrorMessage(message: String)

    fun updateData(playerList: List<Player>)
}