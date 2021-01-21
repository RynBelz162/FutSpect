package com.belzsoftware.futspect.entity.league

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.belzsoftware.futspect.entity.league.LeaguePreference.Companion.TABLE_NAME

@Entity(tableName = TABLE_NAME)
data class LeaguePreference(

    @PrimaryKey
    val id: Int
) {
    companion object {
        const val TABLE_NAME = "league_preference"
    }
}