package com.minitiktok.android.ui

import android.content.Intent
import android.os.Bundle
import androidx.annotation.Nullable
import androidx.appcompat.app.AppCompatActivity
import com.bytedance.sdk.open.aweme.CommonConstants
import com.bytedance.sdk.open.aweme.authorize.model.Authorization
import com.bytedance.sdk.open.aweme.common.handler.IApiEventHandler
import com.bytedance.sdk.open.aweme.common.model.BaseReq
import com.bytedance.sdk.open.aweme.common.model.BaseResp
import com.bytedance.sdk.open.douyin.DouYinOpenApiFactory
import com.minitiktok.android.TikTokApplication
import com.minitiktok.android.extra.logUtils
import com.minitiktok.android.extra.sendToast


class CallBackEntryActivity : AppCompatActivity(), IApiEventHandler {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val douYinOpenApi = DouYinOpenApiFactory.create(this);
        douYinOpenApi.handleIntent(intent, this);
    }

    override fun onReq(req: BaseReq?) {}

    override fun onResp(resp: BaseResp) {
        // 授权成功可以获得authCode
        if (resp.type == CommonConstants.ModeType.SEND_AUTH_RESPONSE) {
            val response = resp as Authorization.Response
            //获得access_token
            TikTokApplication.code = response.authCode
            "登录成功".sendToast()
            finish()
        }
    }

    override fun onErrorIntent(@Nullable intent: Intent?) {
        // 错误数据
        "intent出错啦".sendToast()
        finish()
    }
}