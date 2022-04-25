package com.example.pertemuan8_71190410

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity

class FavoriteActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_favorite)

        setSupportActionBar(findViewById(R.id.toolbar1))
        supportActionBar?.setDisplayShowTitleEnabled(false)
    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_tool, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val in1 = Intent(this, FavoriteActivity::class.java)
        val in2 = Intent(this, PeopleActivity::class.java)
        val in3 = Intent(this, MainActivity::class.java)
        when(item.itemId){
            R.id.favorite -> startActivity(in1)
            R.id.peoples -> startActivity(in2)
            R.id.home -> startActivity(in3)
        }

        return true
    }
}