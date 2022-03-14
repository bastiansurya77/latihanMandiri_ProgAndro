package com.example.pertemuan5_71190410

import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class LoginActivity :AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val editUsername: EditText = findViewById(R.id.editUsername)
        val editPassword: EditText = findViewById(R.id.editPassword)
        val btnLogin: Button = findViewById(R.id.btnLogin)
        var nama: String = editUsername.text.toString()

        btnLogin.setOnClickListener {
            login(editUsername.text.toString(), editPassword.text.toString())
        }
    }

    fun login(username: String, password: String){
        if(password.equals("1234")){
            val i: Intent = Intent(this, MainActivity::class.java)
            i.putExtra("username",username)
            startActivity(i)
        }
        else{
            showMessage("Password Salah")
        }
    }

    fun showMessage(message: String){
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }
}