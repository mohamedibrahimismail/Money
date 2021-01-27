package com.example.money.ui.splash

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import com.example.money.ui.base.BaseActivity
import com.example.money.R
import com.example.money.ui.main.MainActivity

class SplashActivity : BaseActivity() {
    override fun getActivityView(): Int = R.layout.activity_splash

    override fun afterInflation(savedInstance: Bundle?) {
        Handler().postDelayed({
            startActivity(Intent(this@SplashActivity, MainActivity::class.java))
            this@SplashActivity.finish()
        }, 2000)
    }

}