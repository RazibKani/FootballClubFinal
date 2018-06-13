package com.razibkani.footballclubfinal.data.model

import com.google.gson.annotations.SerializedName

data class FootballSearchEventResponse(
        @SerializedName("event") val events: List<Event>
)