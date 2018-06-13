package com.razibkani.footballclubfinal.data.local

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import org.jetbrains.anko.db.*

class FootballClubDbHelper(ctx: Context) : ManagedSQLiteOpenHelper(ctx, "FootballClub.db", null, 1) {

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
        db.createTable(DbFavoriteEvent.TABLE_FAVORITE_MATCH, true,
                DbFavoriteEvent.ID to INTEGER + PRIMARY_KEY + AUTOINCREMENT,
                DbFavoriteEvent.ID_EVENT to TEXT + UNIQUE,
                DbFavoriteEvent.DATE_EVENT to TEXT,
                DbFavoriteEvent.TIME_EVENT to TEXT,
                DbFavoriteEvent.HOME_TEAM_EVENT to TEXT,
                DbFavoriteEvent.AWAY_TEAM_EVENT to TEXT,
                DbFavoriteEvent.HOME_SCORE_EVENT to TEXT,
                DbFavoriteEvent.AWAY_SCORE_EVENT to TEXT)

        db.createTable(DbFavoriteTeam.TABLE_FAVORITE_TEAM, true,
                DbFavoriteTeam.ID to INTEGER + PRIMARY_KEY + AUTOINCREMENT,
                DbFavoriteTeam.ID_TEAM to TEXT + UNIQUE,
                DbFavoriteTeam.NAME_TEAM to TEXT,
                DbFavoriteTeam.FORMED_YEAR_TEAM to TEXT,
                DbFavoriteTeam.STADIUM_TEAM to TEXT,
                DbFavoriteTeam.DESCRIPTION_TEAM to TEXT,
                DbFavoriteTeam.BADGE_TEAM to TEXT,
                DbFavoriteTeam.FANART_TEAM to TEXT)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        // Here you can upgrade tables, as usual
        db.dropTable(DbFavoriteEvent.TABLE_FAVORITE_MATCH, true)
        db.dropTable(DbFavoriteTeam.TABLE_FAVORITE_TEAM, true)
    }
}