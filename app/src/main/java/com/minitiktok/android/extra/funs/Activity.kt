package com.minitiktok.android.extra.funs

import android.app.Activity
import com.bytedance.sdk.open.aweme.authorize.model.Authorization
import com.bytedance.sdk.open.douyin.DouYinOpenApiFactory
import com.minitiktok.android.TikTokApplication
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlin.concurrent.thread

//开启授权
fun Activity.getAccessToken(block: Authorization.Request.() -> Unit): Boolean {
    val douYinOpenApi = DouYinOpenApiFactory.create(this);
    val request = Authorization.Request()
    request.block();
    return douYinOpenApi.authorize(request)
}