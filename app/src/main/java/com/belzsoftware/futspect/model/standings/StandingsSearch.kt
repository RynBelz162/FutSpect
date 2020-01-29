package com.belzsoftware.futspect.model.standings

data class StandingsSearch(
    val results: Int,
    val standings: List<List<Standing>>
)