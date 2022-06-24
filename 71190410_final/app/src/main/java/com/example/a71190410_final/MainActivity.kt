package com.example.a71190410_final

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.firestore.FirebaseFirestore

class MainActivity : AppCompatActivity() {
    val firestore: FirebaseFirestore? = null
    val cari: EditText? by lazy { findViewById(R.id.search) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        signIn()

        val firestore = FirebaseFirestore.getInstance()

        firestore?.collection("film")?.get()!!
            .addOnSuccessListener { docs ->
                val listFilm = ArrayList<Film>()
                listFilm.clear()

                for(doc in docs){
                    listFilm.add(Film("${doc.data["judul"]}", "Genre: ${doc.data["genre"]}",
                        "Nama Produser: ${doc.data["namaProduser"]}", "Pemeran 1: ${doc.data["pemeran1"]}",
                        "Pemeran 2: ${doc.data["pemeran2"]}", "Pemeran 3: ${doc.data["pemeran3"]}",
                        "Tahun Rilis: ${doc.data["thnRilis"]}"))
                }


                val recFilm = findViewById<RecyclerView>(R.id.recFilm)
                recFilm.layoutManager = LinearLayoutManager(this)
                val filmAdapter = FilmAdapter(listFilm)
                recFilm.adapter = filmAdapter
            }
            .addOnFailureListener{
                Log.d("Load Data", "Pengambilan Data Gagal!")
            }

        }


    fun signIn() {
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.webClient))
            .requestEmail()
            .build()
        val mGoogleSignInClient = GoogleSignIn.getClient(this, gso)

        var logout = findViewById<Button>(R.id.logout)
        logout.setOnClickListener {
            mGoogleSignInClient.signOut().addOnCompleteListener {
                val i = Intent(this, Login::class.java)
                Toast.makeText(this, "Logging Out", Toast.LENGTH_SHORT).show()
                startActivity(i)
                finish()
            }
        }

        var insert = findViewById<Button>(R.id.btnInsert)
        insert.setOnClickListener {
            val i = Intent(this, InsertFilm::class.java)
            setContentView(R.layout.activity_insert)
            startActivity(i)
            finish()
        }

    }

}

