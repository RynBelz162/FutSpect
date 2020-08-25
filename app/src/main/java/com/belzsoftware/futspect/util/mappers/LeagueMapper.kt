package com.belzsoftware.futspect.util.mappers

import com.belzsoftware.futspect.entity.league.LeagueEntity
import com.belzsoftware.futspect.model.league.League
import com.belzsoftware.futspect.model.league.LeagueResponse

fun LeagueEntity.map(): League {
    return League(
        this.id,
        this.name,
        this.type,
        this.logo,
        this.country,
        this.flag,
        this.season,
        this.round
    )
}


fun LeagueResponse.reverseMap(): LeagueEntity {
    return LeagueEntity(
        this.league.id,
        this.league.name,
        this.league.type,
        this.league.logo,
        this.country.name,
        this.country.flag,
        this.league.season,
        this.league.round
    )
}