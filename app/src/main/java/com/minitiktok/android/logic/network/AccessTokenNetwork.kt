package com.minitiktok.android.logic.network

import com.minitiktok.android.extra.funs.await

object AccessTokenNetwork {
    private val accessTokenService = ServiceCreator.create<AccessTokenService>()

    suspend fun getAccessToken(
        clientSecret: String,
        code: String,
        clientKey: String
    ) = accessTokenService.getAccessToken(clientSecret, code, clientKey).await();

    suspend fun getClientToken(
        clientKey: String, clientSecret: String
    ) = accessTokenService.getClientToken(clientKey, clientSecret).await()
}