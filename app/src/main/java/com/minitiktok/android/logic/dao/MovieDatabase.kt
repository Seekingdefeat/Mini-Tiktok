package com.minitiktok.android.logic.dao

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.minitiktok.android.logic.model.MovieEntity

@Database(version = 1, entities = [MovieEntity::class])
abstract class MovieDatabase : RoomDatabase() {
    abstract fun MovieDao(): MovieDao

    companion object {
        private var instance: MovieDatabase? = null

        @Synchronized
        fun getInstance(context: Context): MovieDatabase {
            instance?.let {
                return it
            }
            return Room.databaseBuilder(
                context.applicationContext,
                MovieDatabase::class.java,
                "movie_db"
            ).build().apply {
                instance = this
            }
        }
    }
}