package com.razibkani.footballclubfinal.data.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

data class FootballEventResponse(
        @SerializedName("events") val events: List<Event>
)

@Parcelize
data class Event(
        @SerializedName("idEvent") val idEvent: String?,
        @SerializedName("strHomeTeam") val strHomeTeam: String?,
        @SerializedName("strAwayTeam") val strAwayTeam: String?,
        @SerializedName("intHomeScore") val intHomeScore: String?,
        @SerializedName("intAwayScore") val intAwayScore: String?,
        @SerializedName("strTime") val timeEvent: String?,
        @SerializedName("dateEvent") val dateEvent: String?) : Parcelable