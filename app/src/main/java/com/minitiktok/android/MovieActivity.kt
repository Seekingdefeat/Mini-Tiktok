package com.minitiktok.android

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.minitiktok.android.databinding.ActivityMovieBinding

class MovieActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMovieBinding
    private lateinit var view: View

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMovieBinding.inflate(layoutInflater)
        view = binding.root
        setContentView(view)
        binding.button.setOnClickListener {
            TikTokApplication.refreshClientToken()
        }
    }
}