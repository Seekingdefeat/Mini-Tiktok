package com.minitiktok.android.logic.network

import com.minitiktok.android.extra.funs.await

object MovieNetWork {
    private val movieService = ServiceCreator.create<MovieService>()

    suspend fun refreshTvMovies(token: String, version: Int? = null) =
        movieService.getMovies(2, token, version).await()

    suspend fun getTvVersions(token: String, count: Long, cursor: Long? = null) =
        movieService.getVersion(token, count, 2, cursor).await()
}