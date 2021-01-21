package com.belzsoftware.futspect.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.belzsoftware.futspect.data.FutspectDatabase.Companion.DB_VERSION
import com.belzsoftware.futspect.data.league.LeaguesDao
import com.belzsoftware.futspect.entity.league.LeagueEntity
import com.belzsoftware.futspect.entity.league.LeagueFilters
import com.belzsoftware.futspect.entity.league.LeaguePreference

@Database(
    entities = [
        LeagueFilters::class,
        LeagueEntity::class,
        LeaguePreference::class,
    ],
    version = DB_VERSION
)
abstract class FutspectDatabase : RoomDatabase() {

    abstract fun getLeaguesDao(): LeaguesDao

    companion object {
        private const val DB_NAME = "futspect_database"
        const val DB_VERSION = 1

        @Volatile
        private var INSTANCE : FutspectDatabase? = null

        fun getDatabase(context: Context): FutspectDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    FutspectDatabase::class.java,
                    DB_NAME
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}