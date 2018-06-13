package com.razibkani.footballclubfinal.ui.favorites.favoritesteam

import com.razibkani.footballclubfinal.data.model.FootballTeam
import com.razibkani.footballclubfinal.ui.base.MvpView

interface FavoritesTeamMvpView : MvpView {

    fun showLoading()

    fun hideLoading()

    fun updateData(favoritesTeam: List<FootballTeam>)
}