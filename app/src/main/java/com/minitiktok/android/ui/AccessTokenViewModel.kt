package com.minitiktok.android.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.minitiktok.android.TikTokApplication
import com.minitiktok.android.logic.model.AccessTokenResponse
import com.minitiktok.android.logic.network.Repository

class AccessTokenViewModel : ViewModel() {
    private val codeLiveData = MutableLiveData<String>()

    val accessToken = ArrayList<AccessTokenResponse>()

    val tokenLiveData = Transformations.switchMap(codeLiveData) { code ->
        Repository.refreshAccessToken(
            TikTokApplication.CLIENT_SECRET,
            code,
            TikTokApplication.CLIENT_KEY
        )
    }


    fun getToken(code: String) {
        codeLiveData.value = code
    }
}