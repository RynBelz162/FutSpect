package com.belzsoftware.futspect.entity.league

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = LeagueEntity.TABLE_NAME)
data class LeagueEntity(

    @PrimaryKey
    val id: Int,
    val name: String,
    val type: String,
    val logo: String?,
    val country: String,
    val flag: String?,
    val season: Int,
    val round: String
) {
    companion object {
        const val TABLE_NAME = "league"
    }
}