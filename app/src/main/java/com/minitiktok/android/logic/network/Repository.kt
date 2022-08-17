package com.minitiktok.android.logic.network

import com.minitiktok.android.TikTokApplication
import com.minitiktok.android.extra.interfaces.ClientTokenRepository
import com.minitiktok.android.logic.dao.CTDatabase
import com.minitiktok.android.logic.dao.MovieDatabase
import com.minitiktok.android.logic.dao.VersionDatabase
import com.minitiktok.android.logic.model.ClientToken
import com.minitiktok.android.logic.model.ClientTokenResp
import com.minitiktok.android.logic.model.MovieEntity
import com.minitiktok.android.logic.model.Version


object Repository : ClientTokenRepository {
    private val clientTokenDao = CTDatabase.getInstance(TikTokApplication.context).ClientTokenDao()

    private val versionDao = VersionDatabase.getInstance(TikTokApplication.context).VersionDao()

    private val movieDao = MovieDatabase.getInstance(TikTokApplication.context).MovieDao()


    fun insertMovies(movies: List<MovieEntity>) = movieDao.insertMovies(movies)

    fun getAllMovies(): List<MovieEntity> = movieDao.getAllMovies()

    fun getAllVersions(): List<Version> = versionDao.getAllVersions()

    fun insertVersions(versions: List<Version>, now: Long, count: Long, cursor: Long) =
        versionDao.insertVersions(versions, now, count, cursor)

    override fun insertClientToken(token: ClientToken): Long = clientTokenDao.insertToken(token)

    override fun getClientTokenByKey(clientKey: String): List<ClientToken> =
        clientTokenDao.getTokenByKey(clientKey)

    override fun clearTokens() = clientTokenDao.clearTokens()

    override suspend fun getClientTokenByNet(
        clientKey: String,
        clientSecret: String
    ): ClientTokenResp =
        AccessTokenNetwork.getClientToken(clientKey, clientSecret)

}