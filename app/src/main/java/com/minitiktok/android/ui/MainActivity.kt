package com.minitiktok.android.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.minitiktok.android.R
import com.minitiktok.android.TikTokApplication
import com.minitiktok.android.extra.logUtils
import com.minitiktok.android.extra.sendToast
import com.minitiktok.android.logic.model.ClientToken


class MainActivity : AppCompatActivity() {
    val viewModel by lazy { ViewModelProvider(this).get(ClientTokenViewModel::class.java) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewModel.refreshToken()
        viewModel.tokenLiveData.observe(this, Observer {
            val token = it.getOrNull() as ClientToken
            logUtils.d("授权登录", token.access_token ?: "为空")
            if (token.access_token != null) {
                viewModel.setToken(token)
                //全局获取
                TikTokApplication.client_token = token
                //"获取clientToken成功${viewModel.token.access_token}".sendToast()
            } else {
                "获取失败".sendToast()
                it.exceptionOrNull()?.printStackTrace()
            }
        })
    }

    override fun onResume() {
        super.onResume()
    }
}