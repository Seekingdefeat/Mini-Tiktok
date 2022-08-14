package com.minitiktok.android.logic.network

import com.minitiktok.android.extra.throwRunEx
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

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

    private suspend fun <T> Call<T>.await(): T {
        return suspendCoroutine {
            enqueue(object : Callback<T> {
                override fun onResponse(call: Call<T>, response: Response<T>) {
                    val body = response.body()
                    if (body != null) it.resume(body)
                    else it.resumeWithException("response body is null".throwRunEx())
                }

                override fun onFailure(call: Call<T>, t: Throwable) {
                    it.resumeWithException(t)
                }
            })
        }
    }
}