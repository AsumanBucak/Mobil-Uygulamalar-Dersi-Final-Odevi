package com.example.finaluygulama

import android.animation.ObjectAnimator
import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.graphics.Color
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.view.animation.DecelerateInterpolator
import android.widget.ProgressBar
import android.widget.SeekBar
import android.widget.SeekBar.OnSeekBarChangeListener
import androidx.appcompat.app.AppCompatActivity


@SuppressLint("CustomSplashScreen")
class SplashScreenActivity : AppCompatActivity() {

    lateinit var sharedPreferences: SharedPreferences
    lateinit var sharedPreferencesEditor: SharedPreferences.Editor

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        val seekbar: SeekBar = findViewById(R.id.seekbar)
        val progressBar: ProgressBar = findViewById(R.id.progressBar)

        sharedPreferences = getSharedPreferences("shared_pref",Context.MODE_PRIVATE)
        sharedPreferencesEditor = sharedPreferences.edit()



        seekbar.setOnSeekBarChangeListener(object : OnSeekBarChangeListener{
            override fun onProgressChanged(p0: SeekBar?, p1: Int, p2: Boolean) {
                if (p1 > 0) {
                    seekbar.thumb.setTint(Color.parseColor("#074900"))
                } else {
                    seekbar.thumb.setTint(Color.parseColor("#008697"))
                }
            }

            override fun onStartTrackingTouch(p0: SeekBar?) {

            }

            override fun onStopTrackingTouch(p0: SeekBar?) {
                if (p0?.progress == 100) {
                    progressBar.visibility = View.VISIBLE
                    resetProgress(p0)
                    Handler(Looper.getMainLooper()).postDelayed(Runnable {
                        val intent = Intent(this@SplashScreenActivity,GirisActivity::class.java)
                        intent.putExtra("isim",sharedPreferences.getString("isim","Değer Yok"))
                        intent.putExtra("sifre",sharedPreferences.getString("sifre","Değer Yok"))
                        intent.putExtra("unutma",sharedPreferences.getBoolean("unutma",false))
                        startActivity(intent)
                        finish()
                    },1000)
                } else if (p0?.progress != 100) {
                    resetProgress(p0)

                }
            }

        })


    }

    private fun resetProgress(p0: SeekBar?) {
        val animation: ObjectAnimator =
            ObjectAnimator.ofInt(p0, "progress", p0!!.progress, 0)
        animation.duration = 800
        animation.setAutoCancel(true)
        animation.interpolator = DecelerateInterpolator()
        animation.start()
    }
}