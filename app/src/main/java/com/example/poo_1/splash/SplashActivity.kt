package com.example.poo_1.splash

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.poo_1.MainActivity
import com.example.poo_1.R
import dagger.hilt.EntryPoint
import dagger.hilt.android.AndroidEntryPoint
import kotlin.concurrent.thread

@AndroidEntryPoint
class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        val screenSplash= installSplashScreen()

        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_splash)
        screenSplash.setKeepOnScreenCondition { false }
        Thread.sleep(300)

        /*
        val content: View = findViewById(android.R.id.content)
        content.viewTreeObserver.addOnPreDrawListener(
            object : ViewTreeObserver.OnPreDrawListener {
                override fun onPreDraw(): Boolean {
                    // Check if the initial data is ready.
                    return if (viewModel.isReady) {
                        // The content is ready; start drawing.
                        content.viewTreeObserver.removeOnPreDrawListener(this)
                        true
                    } else {
                        // The content is not ready; suspend.
                        false
                    }
                }
            }
        )
        */
        //startActivity(Intent(this@SplashActivity, MainActivity::class.java))
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }
}