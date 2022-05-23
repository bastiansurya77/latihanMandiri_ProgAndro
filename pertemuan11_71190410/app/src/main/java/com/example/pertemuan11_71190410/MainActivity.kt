package com.example.pertemuan11_71190410

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.BaseColumns
import android.widget.*
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val firestore = FirebaseFirestore.getInstance()

        val editNama = findViewById<EditText>(R.id.editNama)
        val editNim = findViewById<EditText>(R.id.editNim)
        val editIpk = findViewById<EditText>(R.id.editIpk)

        val radioAsc = findViewById<RadioButton>(R.id.radioAsc)
        val radioDesc = findViewById<RadioButton>(R.id.radioDesc)

        val radioNama = findViewById<RadioButton>(R.id.radioNama)
        val radioNim = findViewById<RadioButton>(R.id.radioNim)
        val radioIpk = findViewById<RadioButton>(R.id.radioIpk)

        val rg1 = findViewById<RadioGroup>(R.id.rg1)
        val rg2 = findViewById<RadioGroup>(R.id.rg2)

        val btnSimpan = findViewById<Button>(R.id.btnSimpan)
        val btnCari = findViewById<Button>(R.id.btnCari)
        val btnCariData = findViewById<Button>(R.id.btnCariData)
        val txtHasil = findViewById<TextView>(R.id.txtHasil)


        btnSimpan.setOnClickListener {
            val mahasiswa = Mahasiswa(editNim.text.toString(), editNama.text.toString(), editIpk.text.toString().toInt())
            editNim.setText("")
            editNama.setText("")
            editIpk.setText("")
            firestore?.collection("mahasiswa")?.add(mahasiswa)
        }

        btnCari.setOnClickListener {
            if(radioAsc.isChecked()){
                if(radioNim.isChecked()){
                    firestore?.collection("mahasiswa")?.orderBy("nim", Query.Direction.ASCENDING)?.get()!!
                        .addOnSuccessListener { docs ->
                            var output = ""
                            for(doc in docs){
                                output += "\n${doc["nim"]} ${doc["nama"]}: ${doc["ipk"]}"
                            }
                            txtHasil.setText(output)
                        }
                }else if(radioNama.isChecked()){
                    firestore?.collection("mahasiswa")?.orderBy("nama", Query.Direction.ASCENDING)?.get()!!
                        .addOnSuccessListener { docs ->
                            var output = ""
                            for(doc in docs){
                                output += "\n${doc["nim"]} ${doc["nama"]}: ${doc["ipk"]}"
                            }
                            txtHasil.setText(output)
                        }
                }else if(radioIpk.isChecked()){
                    firestore?.collection("mahasiswa")?.orderBy("ipk", Query.Direction.ASCENDING)?.get()!!
                        .addOnSuccessListener { docs ->
                            var output = ""
                            for(doc in docs){
                                output += "\n${doc["nim"]} ${doc["nama"]}: ${doc["ipk"]}"
                            }
                            txtHasil.setText(output)
                        }
                }
            }else if(radioDesc.isChecked()){
                if(radioNim.isChecked()){
                    firestore?.collection("mahasiswa")?.orderBy("nim", Query.Direction.DESCENDING)?.get()!!
                        .addOnSuccessListener { docs ->
                            var output = ""
                            for(doc in docs){
                                output += "\n${doc["nim"]} ${doc["nama"]}: ${doc["ipk"]}"
                            }
                            txtHasil.setText(output)
                        }
                }else if(radioNama.isChecked()){
                    firestore?.collection("mahasiswa")?.orderBy("nama", Query.Direction.DESCENDING)?.get()!!
                        .addOnSuccessListener { docs ->
                            var output = ""
                            for(doc in docs){
                                output += "\n${doc["nim"]} ${doc["nama"]}: ${doc["ipk"]}"
                            }
                            txtHasil.setText(output)
                        }
                }else if(radioIpk.isChecked()){
                    firestore?.collection("mahasiswa")?.orderBy("ipk", Query.Direction.DESCENDING)?.get()!!
                        .addOnSuccessListener { docs ->
                            var output = ""
                            for(doc in docs){
                                output += "\n${doc["nim"]} ${doc["nama"]}: ${doc["ipk"]}"
                            }
                            txtHasil.setText(output)
                        }
                }
            }
            rg1.clearCheck()
            rg2.clearCheck()
        }

        btnCariData.setOnClickListener {
            if(editNim.text.toString() != "" && editNim.text.toString() != null){
                firestore?.collection("mahasiswa")?.whereEqualTo("nim", editNim.text.toString())?.get()!!
                    .addOnSuccessListener { docs ->
                        var output = ""
                        for(doc in docs){
                            output += "\n${doc["nim"]} ${doc["nama"]}: ${doc["ipk"]}"
                        }
                        txtHasil.setText(output)
                    }
            }else if(editNama.text.toString() != "" && editNama.text.toString() != null){
                firestore?.collection("mahasiswa")?.whereEqualTo("nama", editNama.text.toString())?.get()!!
                    .addOnSuccessListener { docs ->
                        var output = ""
                        for(doc in docs){
                            output += "\n${doc["nim"]} ${doc["nama"]}: ${doc["ipk"]}"
                        }
                        txtHasil.setText(output)
                    }
            }else if(editIpk.text.toString() != "" && editNama.text.toString() != null){
                firestore?.collection("mahasiswa")?.whereEqualTo("ipk", editIpk.text.toString())?.get()!!
                    .addOnSuccessListener { docs ->
                        var output = ""
                        for(doc in docs){
                            output += "\n${doc["nim"]} ${doc["nama"]}: ${doc["ipk"]}"
                        }
                        txtHasil.setText(output)
                    }
            }
        }
    }
}