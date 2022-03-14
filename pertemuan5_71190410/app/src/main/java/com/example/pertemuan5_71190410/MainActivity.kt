package com.example.pertemuan5_71190410

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val nama: TextView = findViewById(R.id.namaUser)
        val username = intent.getStringExtra("username")
        nama.setText(username)


        val btnLogout: Button = findViewById(R.id.btnLogout)
        btnLogout.setOnClickListener {
            val i= Intent(this, LoginActivity::class.java)
            startActivity(i)
        }


    }
}