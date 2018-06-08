package com.razibkani.footballclubfinal.ui.base

/**
 * Created by razibkani on 26/04/18.
 */
interface Presenter<in V : MvpView> {

    fun attachView(mvpView: V)

    fun detachView()
}