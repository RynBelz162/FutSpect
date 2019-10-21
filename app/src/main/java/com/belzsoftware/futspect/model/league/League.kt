package com.belzsoftware.futspect.model.league

import com.belzsoftware.futspect.model.shared.Area

data class League(
    val id: Int,
    val name: String,
    var plan: String,
    val area: Area
)