package com.example.pertemuan7_71190410

import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class DetailKontak : AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.detail_kontak)
        val textnama: TextView = findViewById(R.id.textNama)
        val nama = intent.getStringExtra("nama")
        textnama.setText(nama)

        val textnomer: TextView = findViewById(R.id.textNomer)
        val nomer = intent.getStringExtra("nomor")
        textnomer.setText(nomer)

        val textemail: TextView = findViewById(R.id.textEmail)
        val email = intent.getStringExtra("email")
        textemail.setText(email)

        val textttl: TextView = findViewById(R.id.textTtl)
        val ttl = intent.getStringExtra("ttl")
        textttl.setText(ttl)

        val btnKembali: Button = findViewById(R.id.buttonBack)
        btnKembali.setOnClickListener {
            val i = Intent(this, MainActivity::class.java)
            startActivity(i)
        }
    }
}