package com.razibkani.footballclubfinal.data.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

data class FootballPlayerResponse(
        @SerializedName("player") val player: List<Player>
)

@Parcelize
data class Player(
        @SerializedName("idPlayer") val idPlayer: String?,
        @SerializedName("strPlayer") val strPlayer: String?,
        @SerializedName("strDescriptionEN") val strDescriptionEN: String?,
        @SerializedName("strPosition") val strPosition: String?,
        @SerializedName("strHeight") val strHeight: String?,
        @SerializedName("strWeight") val strWeight: String?,
        @SerializedName("strThumb") val strThumb: String?,
        @SerializedName("strCutout") val strCutout: String?
) : Parcelable