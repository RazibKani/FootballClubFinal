package com.razibkani.footballclubfinal.injection.component

import android.content.Context
import com.razibkani.footballclubfinal.FootballClubApp
import com.razibkani.footballclubfinal.data.DataManager
import com.razibkani.footballclubfinal.injection.ApplicationContext
import com.razibkani.footballclubfinal.injection.module.ApplicationModule
import com.razibkani.footballclubfinal.utils.CoroutineContextProvider
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [ApplicationModule::class])
interface ApplicationComponent {

    fun inject(application: FootballClubApp)

    @ApplicationContext
    fun context(): Context

    fun dataManager(): DataManager
    fun coroutineContextProvider(): CoroutineContextProvider
}