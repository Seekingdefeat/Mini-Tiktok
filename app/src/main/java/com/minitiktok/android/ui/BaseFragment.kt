package com.minitiktok.android.ui

import androidx.fragment.app.Fragment
import com.minitiktok.android.TikTokApplication
import com.minitiktok.android.extra.interfaces.WithClientToken
import com.minitiktok.android.extra.interfaces.refreshToken
import com.minitiktok.android.logic.model.isExpire
import com.minitiktok.android.logic.network.Repository
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlin.concurrent.thread

abstract class BaseFragment : Fragment(), WithClientToken {

    fun initWithToken() {
        val clientToken = TikTokApplication.client_token
        val hasNoToken = TikTokApplication.client_token?.isExpire() ?: true
        if (!hasNoToken) {
            onHasToken()
        } else {
            thread {
                GlobalScope.launch {
                    refreshToken(
                        Repository,
                        TikTokApplication.CLIENT_KEY,
                        TikTokApplication.CLIENT_SECRET
                    )
                }
            }
        }
    }

    abstract fun onHasToken()

}