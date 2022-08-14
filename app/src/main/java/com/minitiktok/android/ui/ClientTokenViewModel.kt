package com.minitiktok.android.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.minitiktok.android.TikTokApplication
import com.minitiktok.android.logic.model.ClientToken
import com.minitiktok.android.logic.network.Repository

class ClientTokenViewModel : ViewModel() {
    private val refreshTag = MutableLiveData<String>()

    private var _token = ClientToken()

    val token
        get() = _token

    val tokenLiveData = Transformations.switchMap(refreshTag) {
        Repository.getClientToken(TikTokApplication.CLIENT_KEY, TikTokApplication.CLIENT_SECRET)
    }

    fun refreshToken() {
        refreshTag.value = ""
    }

    fun setToken(token: ClientToken) {
        if (_token.access_token == null) _token = token
    }

}