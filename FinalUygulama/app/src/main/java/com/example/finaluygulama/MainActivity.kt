package com.example.finaluygulama

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.PopupMenu
import android.widget.ProgressBar
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment


class MainActivity : AppCompatActivity() {

    lateinit var sharedPreferences: SharedPreferences
    lateinit var sharedPreferencesEditor: SharedPreferences.Editor

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        sharedPreferences = getSharedPreferences("shared_pref", Context.MODE_PRIVATE)
        sharedPreferencesEditor = sharedPreferences.edit()

        val buttonMenu: Button = findViewById(R.id.buttonMenu)


        showFragment(AcilisFragment())

        buttonMenu.setOnClickListener {
            val popupMenu = PopupMenu(this@MainActivity, buttonMenu)

            popupMenu.menuInflater.inflate(R.menu.menu, popupMenu.menu)
            popupMenu.setOnMenuItemClickListener { menuItem ->
                if (menuItem.itemId == R.id.itemRgb) {
                    showFragment(RgbFragment())
                } else if (menuItem.itemId == R.id.itemSnackbar) {
                    showFragment(SnackBarFragment())
                } else {
                    cikisYap()
                }

                true
            }

            popupMenu.show()
        }
    }

    private fun cikisYap() {
        val alert = AlertDialog.Builder(this)
        val alertView = LayoutInflater.from(this).inflate(R.layout.alert_cikis, null)

        val kullaniciAdi = sharedPreferences.getString("isim", "Değer Yok")
        val kullaniciSifre = sharedPreferences.getString("sifre", "Değer Yok")


        val progressBar = alertView.findViewById<ProgressBar>(R.id.progressBar)
        val editTextIsim = alertView.findViewById<EditText>(R.id.editTextIsim)
        val editTextSifre = alertView.findViewById<EditText>(R.id.editTextSifre)

        alert.create()
        alert.setView(alertView)

        editTextIsim.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

                if (p0.toString() == kullaniciAdi) {
                    if (kullaniciSifre == editTextSifre.text.toString()) {

                        sifirlaVeCik(progressBar)

                    }
                }
            }

            override fun afterTextChanged(p0: Editable?) {
            }

        })

        editTextSifre.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if (p0.toString() == kullaniciSifre) {
                    if (kullaniciAdi == editTextIsim.text.toString()) {
                        sifirlaVeCik(progressBar)
                    }
                }
            }

            override fun afterTextChanged(p0: Editable?) {

            }

        })



        alert.show()


    }

    private fun sifirlaVeCik(progressBar: ProgressBar) {
        sharedPreferencesEditor.putString("isim", "Değer Yok")
        sharedPreferencesEditor.putString("sifre", "Değer Yok")
        sharedPreferencesEditor.putBoolean("unutma", false)
        sharedPreferencesEditor.apply()
        progressBar.visibility = View.VISIBLE
        Handler(Looper.getMainLooper()).postDelayed(Runnable {
            val intent = Intent(this@MainActivity, GirisActivity::class.java)
            startActivity(intent)
            finish()
        }, 600)


    }

    fun showFragment(fragment: Fragment) {
        val fragmentTransition = supportFragmentManager.beginTransaction()
        fragmentTransition.replace(R.id.frameLayout, fragment)
        fragmentTransition.commit()
    }

}