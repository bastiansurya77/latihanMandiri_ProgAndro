package com.example.a71190410_final

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.a71190410_final.databinding.ActivityInsertBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.StorageReference

class EditFilm: AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    private lateinit var fotoPoster: ImageView
    var fstore: FirebaseFirestore? = null
    lateinit var binding: ActivityInsertBinding
    lateinit var imageUri: Uri
    lateinit var stReference: StorageReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit)


        val fstore = FirebaseFirestore.getInstance()
        var film: Film? = null

        val judul = findViewById<EditText>(R.id.editFilm)
        val genre = findViewById<EditText>(R.id.editGenre)
        val namaProduser = findViewById<EditText>(R.id.editNamaProduser)
        val pemeran1 = findViewById<EditText>(R.id.editPemeran1)
        val pemeran2 = findViewById<EditText>(R.id.editPemeran2)
        val pemeran3 = findViewById<EditText>(R.id.editPemeran3)
        val tahunRilis = findViewById<EditText>(R.id.editTahunRilis)
        val btnPoster = findViewById<Button>(R.id.btnPoster)

        judul.setText(intent.getStringExtra("judul"))
        genre.setText(intent.getStringExtra("genre"))
        namaProduser.setText(intent.getStringExtra("namaProduser"))
        pemeran1.setText(intent.getStringExtra("pemeran1"))
        pemeran2.setText(intent.getStringExtra("pemeran2"))
        pemeran3.setText(intent.getStringExtra("pemeran3"))
        tahunRilis.setText(intent.getStringExtra("thnRilis"))

        val btnEdit = findViewById<Button>(R.id.btnEdit)
        val btnBatal = findViewById<Button>(R.id.btnBatal)


        btnEdit.setOnClickListener {
            if (film != null) {
                fstore!!.collection("film").whereEqualTo("judul", film.judul)
                    .get()
                    .addOnSuccessListener {
                        for (doc in it){
                            fstore!!.collection("film").document(doc.id)
                                .update("judul", judul.text.toString(), "genre", genre.text.toString(),
                                    "namaProduser", namaProduser.text.toString(), "pemeran1", pemeran1.text.toString(),
                                    "pemeran2", pemeran2.text.toString(), "pemeran3", pemeran3.text.toString(), "thnRilis", tahunRilis.text.toString())
                                .addOnSuccessListener {
                                    Toast.makeText(this, "Film updated!", Toast.LENGTH_SHORT).show()
//                                    (this as Activity).recreate()
                                }
                        }
                    }
                    .addOnFailureListener {
                        Log.d("Update data", "Data gagal diubah!")
                    }
            }
        }
        btnBatal.setOnClickListener {

        }

    }

}