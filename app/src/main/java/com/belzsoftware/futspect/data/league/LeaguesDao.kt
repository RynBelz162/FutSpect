package com.belzsoftware.futspect.data.league

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.belzsoftware.futspect.entity.league.LeagueFilters

@Dao
interface LeaguesDao {

    @Query("SELECT * FROM ${LeagueFilters.TABLE_NAME} LIMIT 1")
    fun getLeagueFilters(): LeagueFilters

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(filters: LeagueFilters)
}