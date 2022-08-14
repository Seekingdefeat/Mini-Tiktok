package com.minitiktok.android.extra

import android.widget.Toast
import com.minitiktok.android.TikTokApplication

fun String.sendToast(duration: Int = Toast.LENGTH_LONG) {
    Toast.makeText(TikTokApplication.context, this, duration).show()
}

fun String.throwRunEx() = RuntimeException(this)