package com.example.finaluygulama

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.Switch
import android.widget.Toast
@SuppressLint("UseSwitchCompatOrMaterialCode")
class GirisActivity : AppCompatActivity() {

    lateinit var sharedPreferences: SharedPreferences
    lateinit var sharedPreferencesEditor: SharedPreferences.Editor

    val kullaniciAdi = "Asuman BUCAK"
    val kullaniciSifre = "02200201056"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_giris)

        sharedPreferences = getSharedPreferences("shared_pref", Context.MODE_PRIVATE)
        sharedPreferencesEditor = sharedPreferences.edit()



        val editTextIsim: EditText = findViewById(R.id.editTextIsim)
        val editTextSifre: EditText = findViewById(R.id.editTextSifre)
        val switchBeniHatirla: Switch = findViewById(R.id.switchBeniHatirla)
        val progressBar: ProgressBar = findViewById(R.id.progressBar)


        var isim = intent.getStringExtra("isim")
        var sifre = intent.getStringExtra("sifre")
        val unutma = intent.getBooleanExtra("unutma", false)

        if (unutma) {

            switchBeniHatirla.isChecked = true
            editTextIsim.setText(isim)
            editTextSifre.setText(sifre)
            secimeGit(progressBar)
        }

        if (isim == null && sifre == null) {
            isim = "Değer Yok"
            sifre = "Değer Yok"
        }

        Toast.makeText(
            this,
            "Kaydedilmiş \n İsim: $isim \n Şifre: $sifre \n Unutma: $unutma",
            Toast.LENGTH_LONG
        ).show()

        beniHatirlaKontrol(switchBeniHatirla)

        editTextIsim.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                println("beforeTextChanged----" + p0)
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                println("onTextChanged----" + p0)
                if (p0.toString() == kullaniciAdi) {
                    if (kullaniciSifre == editTextSifre.text.toString()) {
                        // TODO: giriş
                        secimeGit(progressBar)

                    }
                }
            }

            override fun afterTextChanged(p0: Editable?) {
                println("afterTextChanged----" + p0.toString())
            }

        })

        editTextSifre.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                println("beforeTextChanged----" + p0)
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                println("onTextChanged----" + p0)
                if (p0.toString() == kullaniciSifre) {
                    if (kullaniciAdi == editTextIsim.text.toString()) {
                        // TODO: giriş
                        secimeGit(progressBar)

                    }
                }
            }

            override fun afterTextChanged(p0: Editable?) {
                println("afterTextChanged----" + p0.toString())
            }

        })


    }

    private fun beniHatirlaKontrol(
        switchBeniHatirla: Switch,
    ) {
        switchBeniHatirla.setOnCheckedChangeListener { compoundButton, beniHatirla ->
            if (beniHatirla) {
                sharedPreferencesEditor.putString("isim",kullaniciAdi)
                sharedPreferencesEditor.putString("sifre",kullaniciSifre)
                sharedPreferencesEditor.putBoolean("unutma",true)
                sharedPreferencesEditor.apply()
            } else {
                sharedPreferencesEditor.putString("isim","Değer Yok")
                sharedPreferencesEditor.putString("sifre","Değer Yok")
                sharedPreferencesEditor.putBoolean("unutma",false)
                sharedPreferencesEditor.apply()
            }
        }
    }

    private fun secimeGit(progressBar: ProgressBar) {
        progressBar.visibility = View.VISIBLE
        Handler(Looper.getMainLooper()).postDelayed(Runnable {
            val intent = Intent(this@GirisActivity,MainActivity::class.java)
            startActivity(intent)
            finish()
        }, 1000)
    }
}