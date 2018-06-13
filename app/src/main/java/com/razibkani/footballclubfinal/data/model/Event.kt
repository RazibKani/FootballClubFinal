package com.razibkani.footballclubfinal.data.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Event(
        val id: Int?, //Need this for database autoincrement
        @SerializedName("idEvent") val idEvent: String?,
        @SerializedName("dateEvent") val dateEvent: String?,
        @SerializedName("strTime") val timeEvent: String?,
        @SerializedName("strHomeTeam") val strHomeTeam: String?,
        @SerializedName("strAwayTeam") val strAwayTeam: String?,
        @SerializedName("intHomeScore") val intHomeScore: String?,
        @SerializedName("intAwayScore") val intAwayScore: String?) : Parcelable