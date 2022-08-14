package com.minitiktok.android

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bytedance.sdk.open.douyin.DouYinOpenApiFactory
import com.bytedance.ies.ugc.aweme:opensdk-common:0.1.9.0
import com.minitiktok.android.logic.model.ClientToken

class TikTokApplication : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.list_layout)
        context = applicationContext
        DouYinOpenApiFactory.init(DouYinOpenConfig(CLIENT_KEY))
        // 在Application的onCreate()中，初始化DouYinOpenApiFactory
        val clientKey = "aw5aucrvxokli37i"

    }
    companion object {
        @Suppress("StaticFieldLeak")
        lateinit var context: Context
        const val CLIENT_KEY = "aw5aucrvxokli37i"
        const val CLIENT_SECRET = "4f7e2faf07f89de2df59e0340feae4f2"
        var client_token: ClientToken? = null
    }
}