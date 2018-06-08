package com.razibkani.footballclubfinal.injection.module

import android.app.Application
import android.content.Context
import com.razibkani.footballclubfinal.data.DataManager
import com.razibkani.footballclubfinal.data.local.FootballClubDbHelper
import com.razibkani.footballclubfinal.data.remote.ApiService
import com.razibkani.footballclubfinal.injection.ApplicationContext
import com.razibkani.footballclubfinal.utils.CoroutineContextProvider
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ApplicationModule(private val application: Application) {

    @Provides
    fun provideApplication(): Application = application

    @Provides
    @ApplicationContext
    fun provideContext(): Context = application

    @Provides
    @Singleton
    fun provideApiService(): ApiService = ApiService.create()

    @Provides
    @Singleton
    fun provideDbHelper(): FootballClubDbHelper = FootballClubDbHelper.getInstance(application)

    @Provides
    @Singleton
    fun provideDataManager(): DataManager = DataManager(provideApiService(), provideDbHelper())

    @Provides
    @Singleton
    fun coroutineContextProvider(): CoroutineContextProvider = CoroutineContextProvider()
}