package com.example.pertemuan7_71190410

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val listKontak = ArrayList<Kontak>()
        listKontak.add(Kontak("Jon Snow", "081212565608", R.mipmap.icon1,
            "jonsnow1@gmail.com", "29 Febuari 1996"))
        listKontak.add(Kontak("Sherlock Holmes", "085600112233", R.mipmap.icon2,
        "sherlock77@gmail.com", "1 Januari 1890"))
        listKontak.add(Kontak("Lady Whistledown", "085755667788", R.mipmap.icon3,
        "whistle123@gmail.com", "11 Januari 2001"))

        val recKontak = findViewById<RecyclerView>(R.id.recKontak)
        recKontak.layoutManager = LinearLayoutManager(this)
        val kontakAdapter = KontakAdapter(listKontak)
        recKontak.adapter = kontakAdapter
    }
}