package com.example.sohag_job2_profile_registration_application

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper

class WelcomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome)

        Handler(Looper.getMainLooper()).postDelayed({
            startActivity(Intent(this@WelcomeActivity, MainActivity::class.java))
            finish()
        }, 3000)
    }
}