package com.razibkani.footballclubfinal.data.model

import com.google.gson.annotations.SerializedName

data class FootballEventResponse(
        @SerializedName("events") val events: List<Event>?
)