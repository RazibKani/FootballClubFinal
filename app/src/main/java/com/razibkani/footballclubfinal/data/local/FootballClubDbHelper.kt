package com.razibkani.footballclubfinal.data.local

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import com.razibkani.footballclubfinal.data.model.FavoriteEvent
import org.jetbrains.anko.db.*

class FootballClubDbHelper(ctx: Context) : ManagedSQLiteOpenHelper(ctx, "FavoriteEvent.db", null, 1) {

    companion object {
        private var instance: FootballClubDbHelper? = null

        @Synchronized
        fun getInstance(ctx: Context): FootballClubDbHelper {
            if (instance == null) {
                instance = FootballClubDbHelper(ctx.applicationContext)
            }
            return instance as FootballClubDbHelper
        }
    }

    override fun onCreate(db: SQLiteDatabase) {
        // Here you create tables
        db.createTable(FavoriteEvent.TABLE_FAVORITE, true,
                FavoriteEvent.ID to INTEGER + PRIMARY_KEY + AUTOINCREMENT,
                FavoriteEvent.ID_EVENT to TEXT + UNIQUE,
                FavoriteEvent.DATE_EVENT to TEXT,
                FavoriteEvent.TIME_EVENT to TEXT,
                FavoriteEvent.HOME_TEAM_EVENT to TEXT,
                FavoriteEvent.AWAY_TEAM_EVENT to TEXT,
                FavoriteEvent.HOME_SCORE_EVENT to INTEGER,
                FavoriteEvent.AWAY_SCORE_EVENT to INTEGER)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        // Here you can upgrade tables, as usual
        db.dropTable(FavoriteEvent.TABLE_FAVORITE, true)
    }
}