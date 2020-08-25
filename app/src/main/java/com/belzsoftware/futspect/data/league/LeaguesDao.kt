package com.belzsoftware.futspect.data.league

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.belzsoftware.futspect.entity.league.LeagueEntity
import com.belzsoftware.futspect.entity.league.LeagueFilters
import kotlinx.coroutines.flow.Flow

@Dao
interface LeaguesDao {

    @Query("SELECT * FROM ${LeagueFilters.TABLE_NAME} LIMIT 1")
    fun getLeagueFilters(): Flow<LeagueFilters?>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFilters(filters: LeagueFilters)

    @Insert(onConflict =  OnConflictStrategy.REPLACE)
    suspend fun insertLeagues(leagues: List<LeagueEntity>)

    @Query("SELECT * FROM ${LeagueEntity.TABLE_NAME}")
    fun getLeagues(): List<LeagueEntity>
}