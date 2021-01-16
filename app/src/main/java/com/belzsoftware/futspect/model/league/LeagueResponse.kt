package com.belzsoftware.futspect.model.league

import android.os.Parcelable
import com.belzsoftware.futspect.model.country.Country
import com.belzsoftware.futspect.model.season.Season
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize

@Parcelize
@JsonClass(generateAdapter = true)
data class LeagueResponse(
    val league: League,
    val country: Country,
    val seasons: List<Season>
) : Parcelable