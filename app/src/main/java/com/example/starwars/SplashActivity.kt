package com.example.starwars

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.animation.AnimationUtils
import android.widget.ImageView

class SplashActivity : AppCompatActivity() {
    private lateinit var appTextBack: ImageView
    override fun onStart() {
        super.onStart()
        window.statusBarColor = resources.getColor(R.color.white)
        window.navigationBarColor = resources.getColor(R.color.white)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        supportActionBar?.hide()
        appTextBack = findViewById(R.id.splash_screen_app_logo_iv)
        appTextBack.startAnimation(AnimationUtils.loadAnimation(this, R.anim.scale_increase_anim))
        Handler().postDelayed({
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }, 1200)
    }
}