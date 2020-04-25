package com.belzsoftware.futspect.entity.league

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.belzsoftware.futspect.entity.league.LeagueFilters.Companion.TABLE_NAME

@Entity(tableName = TABLE_NAME)
data class LeagueFilters(
    @PrimaryKey
    val id: Int = 1,
    val searchTerm: String,
    val isLeagueChecked: Boolean,
    val isCupChecked: Boolean
) {
    companion object {
        const val TABLE_NAME = "league_filters"
    }
}