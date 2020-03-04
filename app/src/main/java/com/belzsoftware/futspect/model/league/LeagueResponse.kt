package com.belzsoftware.futspect.model.league

import android.os.Parcelable
import com.belzsoftware.futspect.model.country.Country
import com.belzsoftware.futspect.model.season.Season
import kotlinx.android.parcel.Parcelize

@Parcelize
data class LeagueResponse(
    val league: League,
    val country: Country,
    val seasons: List<Season>
) : Parcelable