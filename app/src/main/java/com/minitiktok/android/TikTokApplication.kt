package com.minitiktok.android

import android.app.Application
import android.content.Context
import com.bytedance.sdk.open.douyin.DouYinOpenApiFactory
import com.bytedance.sdk.open.douyin.DouYinOpenConfig
import com.minitiktok.android.extra.funs.logUtils
import com.minitiktok.android.extra.interfaces.WithClientToken
import com.minitiktok.android.extra.interfaces.refreshToken
import com.minitiktok.android.logic.model.ClientToken
import com.minitiktok.android.logic.network.Repository
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlin.concurrent.thread

class TikTokApplication : Application() {
    companion object : WithClientToken {
        @Suppress("StaticFieldLeak")
        lateinit var context: Context
        const val CLIENT_KEY = "aw5aucrvxokli37i"
        const val CLIENT_SECRET = "4f7e2faf07f89de2df59e0340feae4f2"
        var client_token: ClientToken? = null
        var code: String? = null

        //提供一个随时刷新client_token的方法，不限于在activity或fragment
        fun refreshClientToken() {
            thread {
                GlobalScope.launch {
                    refreshToken(Repository, CLIENT_KEY, CLIENT_SECRET)
                }
            }
        }

        override fun onTokenByDb(result: ClientToken): (() -> Unit)? = {
            logUtils.d("授权登录", "刷新成功，从数据库取得：${result.access_token}")
            client_token = result
        }

        override fun onTokenByNet(result: ClientToken): (() -> Unit)? = {
            logUtils.d("授权登录", "刷新成功，从网络取得：${result.access_token}")
            client_token = result
        }

        override fun onTokenFailure(result: String): () -> Unit = {
            logUtils.d("授权登录", "刷新失败,$result")
        }

    }


    override fun onCreate() {
        super.onCreate()
        context = applicationContext
        DouYinOpenApiFactory.init(DouYinOpenConfig(CLIENT_KEY))
    }

}