package com.minitiktok.android.logic.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import com.minitiktok.android.logic.model.MovieEntity

@Dao
interface MovieDao {
    @Insert
    fun insertMovie(movie: MovieEntity): Long

    @Transaction
    fun insertMovies(movies: List<MovieEntity>) {
        for (movie in movies) {
            insertMovie(movie)
        }
    }

    @Query("select * from MovieEntity")
    fun getAllMovies(): List<MovieEntity>
}