package com.example.pertemuan9_71190410

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.*
import androidx.core.widget.addTextChangedListener

class MainActivity : AppCompatActivity() {
    var sp: SharedPreferences? = null
    var spEdit: SharedPreferences.Editor? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        sp = getSharedPreferences("mySP", MODE_PRIVATE)
        spEdit = sp?.edit()

        if(sp?.getBoolean("isLogin", false) == true){
            setContentView(R.layout.activity_home)
            val btnLogout = findViewById<Button>(R.id.btnLogout)
            btnLogout.setOnClickListener {
                logout()
            }

            val editBahasa = findViewById<EditText>(R.id.editBahasa)
            editBahasa.setText(sp!!.getString("bahasa","").toString())
            editBahasa.addTextChangedListener {it ->
                spEdit?.putString("bahasa", it.toString())
                spEdit?.commit()
            }

            val editUkuran = findViewById<EditText>(R.id.editUkuran)
            editUkuran.setText(sp!!.getString("ukuran","11").toString())
            editUkuran.addTextChangedListener { it ->
                spEdit?.putString("ukuran", it.toString())
                spEdit?.commit()
            }
        }
        else{
            setContentView(R.layout.activity_main)
            val editUsername = findViewById<EditText>(R.id.username)
            val editPassword = findViewById<EditText>(R.id.password)
            val btnLogin = findViewById<Button>(R.id.btnLogin)

            btnLogin.setOnClickListener {
                login(editUsername.text.toString(), editPassword.text.toString())
            }
        }
    }
    fun login(username: String, password: String){
        if(username.equals("admin") && password.equals("1234")){
            spEdit?.putBoolean("isLogin", true)
            spEdit?.commit()

            val i = Intent(this, MainActivity::class.java)
            startActivity(i)
            finish()
        }else{
            Toast.makeText(this, "Username & Password Salah!", Toast.LENGTH_LONG).show()
        }
    }
    fun logout(){
        spEdit?.putBoolean("isLogin", false)
        spEdit?.commit()

        val i = Intent(this, MainActivity::class.java)
        startActivity(i)
        finish()
    }
}