package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.WindowManager
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private val SPLASH_SCREEN: Long = 5000

    // Variabel animasi
    lateinit var topAnim: Animation
    lateinit var image: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
        setContentView(R.layout.activity_main)

        // Panggil animasi
        topAnim = AnimationUtils.loadAnimation(this, R.anim.top_animation)

        // Hooks
        image = findViewById(R.id.imageView)

        // Mulai animasi
        image.startAnimation(topAnim)

        // Delay dan navigasi ke activity Dashboard
        Handler().postDelayed({
            val intent = Intent(this@MainActivity, Dashboard::class.java)
            startActivity(intent)
            finish()
        }, SPLASH_SCREEN)
    }
}
