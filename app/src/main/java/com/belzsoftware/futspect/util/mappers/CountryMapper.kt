package com.belzsoftware.futspect.util.mappers

import com.belzsoftware.futspect.entity.league.LeagueEntity
import com.belzsoftware.futspect.model.country.Country

fun LeagueEntity.mapToCountry(): Country {
    return Country(
        this.country,
        null,
        null
    )
}