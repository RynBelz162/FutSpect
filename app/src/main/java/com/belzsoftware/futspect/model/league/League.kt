package com.belzsoftware.futspect.model.league

import com.google.gson.annotations.SerializedName

data class League(
    @SerializedName("league_id") val id: Int,
    val name: String,
    val country: String,
    @SerializedName("country_code") val countryCode: String,
    val logo: String,
    val standings: Int
)