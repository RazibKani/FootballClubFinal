package com.razibkani.footballclubfinal.data

import android.database.sqlite.SQLiteConstraintException
import com.razibkani.footballclubfinal.data.local.DbCallback
import com.razibkani.footballclubfinal.data.local.DbFavoriteEvent
import com.razibkani.footballclubfinal.data.local.DbFavoriteTeam
import com.razibkani.footballclubfinal.data.local.FootballClubDbHelper
import com.razibkani.footballclubfinal.data.model.*
import com.razibkani.footballclubfinal.data.remote.ApiService
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.delete
import org.jetbrains.anko.db.insert
import org.jetbrains.anko.db.select

class DataManager(private val apiService: ApiService,
                  private val database: FootballClubDbHelper) {

    suspend fun getFootballPrevEvent(leagueId: String): FootballEventResponse {
        return apiService.getFootballPrevEvent(leagueId).await()
    }

    suspend fun getFootballNextEvent(leagueId: String): FootballEventResponse {
        return apiService.getFootballNextEvent(leagueId).await()
    }

    suspend fun getFootballDetailEvent(idEvent: String): FootballDetailEventResponse {
        return apiService.getFootballDetailEvent(idEvent).await()
    }

    suspend fun getFootballClubDetail(clubName: String): FootballClubResponse {
        return apiService.getFootballClubDetail(clubName).await()
    }

    suspend fun getFootballTeams(leagueName: String): FootballTeamResponse {
        return apiService.getTeams(leagueName).await()
    }

    suspend fun getPlayerByTeamName(teamName: String): FootballPlayerResponse {
        return apiService.getPlayerByTeamName(teamName).await()
    }

    suspend fun getTeamByName(teamName: String): FootballTeamResponse {
        return apiService.getTeamsByName(teamName).await()
    }

    suspend fun getEventByKeyword(keyword: String): FootballSearchEventResponse {
        return apiService.getEventByKeyword(keyword).await()
    }

    fun addMatchToFavorite(event: Event, callback: DbCallback) {
        try {
            database.use {
                insert(DbFavoriteEvent.TABLE_FAVORITE_MATCH,
                        DbFavoriteEvent.ID_EVENT to event.idEvent,
                        DbFavoriteEvent.DATE_EVENT to event.dateEvent,
                        DbFavoriteEvent.TIME_EVENT to event.timeEvent,
                        DbFavoriteEvent.HOME_TEAM_EVENT to event.strHomeTeam,
                        DbFavoriteEvent.AWAY_TEAM_EVENT to event.strAwayTeam,
                        DbFavoriteEvent.HOME_SCORE_EVENT to event.intHomeScore,
                        DbFavoriteEvent.AWAY_SCORE_EVENT to event.intAwayScore)

            }
            callback.onSuccess()
        } catch (e: SQLiteConstraintException) {
            callback.onFailed()
        }
    }

    fun removeMatchFromFavorite(eventId: String, callback: DbCallback) {
        try {
            database.use {
                delete(DbFavoriteEvent.TABLE_FAVORITE_MATCH, "(EVENT_ID = {id})",
                        "id" to eventId)
            }
            callback.onSuccess()
        } catch (e: SQLiteConstraintException) {
            callback.onFailed()
        }
    }

    fun getFavoritesMatch(): List<Event> {
        var favorites: List<Event> = ArrayList()
        try {
            database.use {
                val result = select(DbFavoriteEvent.TABLE_FAVORITE_MATCH)
                favorites = result.parseList(classParser())
            }
        } catch (e: SQLiteConstraintException) {
            favorites = ArrayList()
        }
        return favorites
    }

    fun favoriteMatchState(eventId: String): Boolean {
        var isFavorite = false
        try {
            database.use {
                val result = select(DbFavoriteEvent.TABLE_FAVORITE_MATCH)
                        .whereArgs("(EVENT_ID = {id})",
                                "id" to eventId)
                val favorite = result.parseList(classParser<Event>())
                if (!favorite.isEmpty()) isFavorite = true
            }
        } catch (e: SQLiteConstraintException) {
            isFavorite = false
        }
        return isFavorite
    }

    fun addTeamToFavorite(team: FootballTeam, callback: DbCallback) {
        try {
            database.use {
                insert(DbFavoriteTeam.TABLE_FAVORITE_TEAM,
                        DbFavoriteTeam.ID_TEAM to team.idTeam,
                        DbFavoriteTeam.NAME_TEAM to team.strTeam,
                        DbFavoriteTeam.FORMED_YEAR_TEAM to team.intFormedYear,
                        DbFavoriteTeam.STADIUM_TEAM to team.strStadium,
                        DbFavoriteTeam.DESCRIPTION_TEAM to team.strDescriptionEN,
                        DbFavoriteTeam.BADGE_TEAM to team.strTeamBadge,
                        DbFavoriteTeam.FANART_TEAM to team.strTeamFanart)

            }
            callback.onSuccess()
        } catch (e: SQLiteConstraintException) {
            callback.onFailed()
        }
    }

    fun removeTeamFromFavorite(teamId: String, callback: DbCallback) {
        try {
            database.use {
                delete(DbFavoriteTeam.TABLE_FAVORITE_TEAM, "(TEAM_ID = {id})",
                        "id" to teamId)
            }
            callback.onSuccess()
        } catch (e: SQLiteConstraintException) {
            callback.onFailed()
        }
    }

    fun getFavoritesTeam(): List<FootballTeam> {
        var favorites: List<FootballTeam> = ArrayList()
        try {
            database.use {
                val result = select(DbFavoriteTeam.TABLE_FAVORITE_TEAM)
                favorites = result.parseList(classParser())
            }
        } catch (e: SQLiteConstraintException) {
            favorites = ArrayList()
        }
        return favorites
    }

    fun favoriteTeamState(teamId: String): Boolean {
        var isFavorite = false
        try {
            database.use {
                val result = select(DbFavoriteTeam.TABLE_FAVORITE_TEAM)
                        .whereArgs("(TEAM_ID = {id})",
                                "id" to teamId)
                val favorite = result.parseList(classParser<FootballTeam>())
                if (!favorite.isEmpty()) isFavorite = true
            }
        } catch (e: SQLiteConstraintException) {
            isFavorite = false
        }
        return isFavorite
    }

    fun getLeagues(): List<FootballLeague> {
        val leagues: MutableList<FootballLeague> = mutableListOf()
        leagues.add(0, FootballLeague("4328", "English Premier League", false))
        leagues.add(1, FootballLeague("4332", "Italian Serie A", false))
        leagues.add(2, FootballLeague("4334", "French Ligue 1", false))
        leagues.add(3, FootballLeague("4335", "Spanish La Liga", false))
        leagues.add(4, FootballLeague("4331", "German Bundesliga", false))

        return leagues.toList()
    }
}