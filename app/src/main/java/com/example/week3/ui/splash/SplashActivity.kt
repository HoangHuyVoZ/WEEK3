package com.example.week3.ui.splash

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.example.week3.R
import com.example.week3.ui.intro.IntroActivity
import com.example.week3.ui.main.MainActivity
import com.example.week3.utils.Constants.Companion.FIRST_TIME
import com.example.week3.utils.Constants.Companion.PREFS_NAME

@Suppress("DEPRECATION")
class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        Handler().postDelayed({
            val pref = getSharedPreferences(PREFS_NAME, AppCompatActivity.MODE_PRIVATE)
            val firstTime= pref.getBoolean(FIRST_TIME,true)
            if(!firstTime){
                startActivity(Intent(this, IntroActivity::class.java))
            }else{
                startActivity(Intent(this, MainActivity::class.java))
            }

        }, 2000)


        }
    }
