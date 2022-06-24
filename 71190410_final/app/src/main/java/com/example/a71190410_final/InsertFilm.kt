package com.example.a71190410_final

import android.app.ProgressDialog
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.PersistableBundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.example.a71190410_final.databinding.ActivityInsertBinding
import com.example.a71190410_final.databinding.ActivityMainBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import java.text.SimpleDateFormat
import java.util.*

@Suppress("DEPRECATION")
class InsertFilm : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private lateinit var fotoPoster: ImageView
    var fstore: FirebaseFirestore? = null
    lateinit var binding: ActivityInsertBinding
    lateinit var imageUri: Uri
    lateinit var stReference: StorageReference
    lateinit var progressDialog: ProgressDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_insert)
        binding = ActivityInsertBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val auth = FirebaseAuth.getInstance()
        val fstore = FirebaseFirestore.getInstance()

        val nama = findViewById<EditText>(R.id.editFilm)
        val genre = findViewById<EditText>(R.id.editGenre)
        val namaProduser = findViewById<EditText>(R.id.editNamaProduser)
        val pemeran1 = findViewById<EditText>(R.id.editPemeran1)
        val pemeran2 = findViewById<EditText>(R.id.editPemeran2)
        val pemeran3 = findViewById<EditText>(R.id.editPemeran3)
        val tahunRilis = findViewById<EditText>(R.id.editTahunRilis)
        val btnPoster = findViewById<Button>(R.id.btnPoster)

        val btnSimpan = findViewById<Button>(R.id.btnSimpan)
        val btnBatal = findViewById<Button>(R.id.btnBatal)

        binding.btnPoster.setOnClickListener {
            pilihPoster()
        }

        btnSimpan.setOnClickListener {
            if(nama.text.toString().isEmpty()){
                nama.error = "Nama film tidak boleh kosong!"
                nama.requestFocus()
                return@setOnClickListener
            }


            if(genre.text.toString().isEmpty()){
                genre.error = "Genre tidak boleh kosong!"
                genre.requestFocus()
                return@setOnClickListener
            }

            if(namaProduser.text.toString().isEmpty()){
                namaProduser.error = "Nama Produser tidak boleh kosong!"
                namaProduser.requestFocus()
                return@setOnClickListener
            }

//            if(poster.text.toString().isEmpty()) {
//                poster.error = "Poster tidak boleh kosong"
//                poster.requestFocus()
//                return@setOnClickListener
//            }

            if(pemeran1.text.toString().isEmpty()) {
                pemeran1.error = "Pemeran Pertama tidak boleh kosong"
                pemeran1.requestFocus()
                return@setOnClickListener
            }

            if(pemeran2.text.toString().isEmpty()) {
                pemeran2.error = "Pemeran Kedua tidak boleh kosong"
                pemeran2.requestFocus()
                return@setOnClickListener
            }

            if(pemeran3.text.toString().isEmpty()) {
                pemeran3.error = "Pemeran Ketiga tidak boleh kosong"
                pemeran3.requestFocus()
                return@setOnClickListener
            }

            if(tahunRilis.text.toString().isEmpty()) {
                tahunRilis.error = "Tahun Rilis tidak boleh kosong"
                tahunRilis.requestFocus()
                return@setOnClickListener
            }
            simpanPoster()
            val film = Film(nama.text.toString(), genre.text.toString(), namaProduser.text.toString(), pemeran1.text.toString(), pemeran2.text.toString(), pemeran3.text.toString(), tahunRilis.text.toString())
            fstore?.collection("film")?.add(film)
                ?.addOnSuccessListener {
                    Toast.makeText(this, "Data berhasil dimasukkan!", Toast.LENGTH_SHORT).show()
                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                    finish()
                }?.addOnFailureListener{
                    Toast.makeText(this, "Data gagal ditambahkan!", Toast.LENGTH_SHORT).show()
                }
        }
    }

    private fun simpanPoster() {


        val formatTanggal = SimpleDateFormat("dd_MM_yyyy_HH_mm_ss", Locale.ENGLISH)
        val now = Date()
        val namaFile: String = formatTanggal.format(now)
        stReference = FirebaseStorage.getInstance().getReference("images/$namaFile")

        stReference.putFile(imageUri)
            .addOnSuccessListener {
                binding.poster.setImageURI(null)

            }
    }

    private fun pilihPoster() {
        val intent = Intent()
        intent.setType("image/*")
        intent.setAction(Intent.ACTION_GET_CONTENT)
        startActivityForResult(intent, 100)

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if(requestCode==100 && data!=null && data.getData()!! != null){
            imageUri = data.getData()!!
            binding.poster.setImageURI(imageUri)

        }
    }

    fun btnBatal(view: android.view.View) {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }
}
