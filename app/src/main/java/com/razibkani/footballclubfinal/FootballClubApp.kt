package com.razibkani.footballclubfinal

import android.app.Application
import android.content.Context
import com.razibkani.footballclubfinal.injection.component.ApplicationComponent
import com.razibkani.footballclubfinal.injection.component.DaggerApplicationComponent
import com.razibkani.footballclubfinal.injection.module.ApplicationModule

class FootballClubApp : Application() {

    private lateinit var applicationComponent: ApplicationComponent

    override fun onCreate() {
        super.onCreate()

        applicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(ApplicationModule(this)).build()
        applicationComponent.inject(this)
    }

    companion object {
        fun get(context: Context): FootballClubApp {
            return context.applicationContext as FootballClubApp
        }
    }

    fun getComponent(): ApplicationComponent {
        return applicationComponent
    }
}