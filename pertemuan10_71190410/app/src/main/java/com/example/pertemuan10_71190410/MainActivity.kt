package com.example.pertemuan10_71190410

import android.annotation.SuppressLint
import android.content.ContentValues
import android.database.sqlite.SQLiteDatabase
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.BaseColumns
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    lateinit var db: SQLiteDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val dbHelper = DatabaseHelper(this)
        db = dbHelper.writableDatabase

        val etNama = findViewById<EditText>(R.id.etNama)
        val etUsia = findViewById<EditText>(R.id.etUsia)
        val btnSimpan = findViewById<Button>(R.id.btnSimpan)
        val btnHapus = findViewById<Button>(R.id.btnHapus)
        val btnCari = findViewById<Button>(R.id.btnCari)

        btnSimpan.setOnClickListener {
            saveData(etNama.text.toString(), etUsia.text.toString())
        }
        btnHapus.setOnClickListener {
            deleteData(etNama.text.toString(), etUsia.text.toString())
        }
        btnCari.setOnClickListener {
            searchData(etNama.text.toString(), etUsia.text.toString())
        }
        refreshData()
    }

    fun saveData(nama: String, usia: String){
        val values = ContentValues().apply {
            put(DatabaseContract.Penduduk.COLUMN_NAME_NAMA, nama)
            put(DatabaseContract.Penduduk.COLUMN_NAME_USIA, usia)
        }
        db.insert(DatabaseContract.Penduduk.TABLE_NAME, null, values)
        refreshData()
    }

    fun deleteData(nama: String, usia: String){
        val selection = "${DatabaseContract.Penduduk.COLUMN_NAME_NAMA} LIKE ? OR "+
                "${DatabaseContract.Penduduk.COLUMN_NAME_USIA} LIKE ?"
        val selectionArgs = arrayOf(nama, usia)
        val deletedRows = db.delete(DatabaseContract.Penduduk.TABLE_NAME, selection, selectionArgs)
        refreshData()
    }

    @SuppressLint("Range")
    fun searchData(nama: String, usia: String){
        val query = "SELECT * FROM ${DatabaseContract.Penduduk.TABLE_NAME} WHERE ${DatabaseContract.Penduduk.COLUMN_NAME_NAMA} LIKE ? OR "+
                "${DatabaseContract.Penduduk.COLUMN_NAME_USIA} LIKE ?"
        val selectionArgs = arrayOf(nama, usia)
        val cursor = db.rawQuery(
            query,
            selectionArgs
        )
        var result = ""
        with(cursor!!) {
            while (moveToNext()) {
                val nama = getString(getColumnIndex(DatabaseContract.Penduduk.COLUMN_NAME_NAMA))
                val usia = getString(getColumnIndex(DatabaseContract.Penduduk.COLUMN_NAME_USIA))
                result += "${nama} - ${usia} tahun\n"
            }
        }
        val tvHasilCari = findViewById<TextView>(R.id.tvHasilCari)
        tvHasilCari.text = result
    }

    @SuppressLint("Range")
    fun refreshData(){
        val columns = arrayOf(
            BaseColumns._ID,
            DatabaseContract.Penduduk.COLUMN_NAME_NAMA,
            DatabaseContract.Penduduk.COLUMN_NAME_USIA
        )
        val cursor = db.query(
            DatabaseContract.Penduduk.TABLE_NAME,
            columns,
            null,
            null,
            null,
            null,
            null
        )
        var result = ""
        with(cursor!!) {
            while (moveToNext()) {
                val nama = getString(getColumnIndex(DatabaseContract.Penduduk.COLUMN_NAME_NAMA))
                val usia = getString(getColumnIndex(DatabaseContract.Penduduk.COLUMN_NAME_USIA))
                result += "${nama} - ${usia} tahun\n"
            }
        }
        val tvHasil = findViewById<TextView>(R.id.tvHasil)
        tvHasil.text = result
    }
}