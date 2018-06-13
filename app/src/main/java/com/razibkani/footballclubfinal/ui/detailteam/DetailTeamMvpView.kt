package com.razibkani.footballclubfinal.ui.detailteam

import com.razibkani.footballclubfinal.ui.base.MvpView

interface DetailTeamMvpView : MvpView {

    fun showSuccessMessage(successMessage: String)

    fun showErrorMessage(errorMessage: String)
}