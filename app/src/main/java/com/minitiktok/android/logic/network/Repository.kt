package com.minitiktok.android.logic.network

import androidx.lifecycle.liveData
import com.minitiktok.android.TikTokApplication
import com.minitiktok.android.extra.logUtils
import com.minitiktok.android.extra.throwRunEx
import com.minitiktok.android.logic.dao.CTDatabase
import com.minitiktok.android.logic.model.toEntity
import kotlinx.coroutines.Dispatchers


object Repository {
    private val clientTokenDao = CTDatabase.getInstance(TikTokApplication.context).ClientTokenDao()

    fun refreshAccessToken(clientSecret: String, code: String, clientKey: String) =
        liveData(Dispatchers.IO) {
            logUtils.d("授权登录", "测试")
            val result = try {
                val accessTokenResp =
                    AccessTokenNetwork.getAccessToken(clientSecret, code, clientKey)
                if (accessTokenResp.respData.errorCode == 0) {
                    Result.success(accessTokenResp.respData)
                } else {
                    Result.failure("响应码错误：${accessTokenResp.respData.errorCode}".throwRunEx())
                }
            } catch (e: Exception) {
                Result.failure(e)
            }
            emit(result)
        }

    private fun refreshClientToken(clientKey: String, clientSecret: String) =
        liveData(Dispatchers.IO) {
            val result = try {
                val resp = clientTokenDao.getTokenByKey(clientKey)
                if (resp.isNotEmpty()) {
                    //超时(时限2个小时)需要重新获取，暂时默认不会超时
                    Result.success(resp[0])
                } else {
                    //重新申请token
                    val networkResp = AccessTokenNetwork.getClientToken(clientKey, clientSecret)
                    if (networkResp.respData.error_code == 0) {
                        //封装为一个ClientToken
                        val token = networkResp.respData.toEntity(TikTokApplication.CLIENT_KEY)
                        //插入数据库
                        clientTokenDao.insertToken(token)
                        Result.success(token)
                    } else {

                        Result.failure("响应码错误：${networkResp.respData.error_code}".throwRunEx())
                    }
                }
            } catch (e: Exception) {
                Result.failure(e)
            }
            emit(result)
        }

    fun getClientToken(clientKey: String, clientSecret: String) =
        refreshClientToken(clientKey, clientSecret)

}