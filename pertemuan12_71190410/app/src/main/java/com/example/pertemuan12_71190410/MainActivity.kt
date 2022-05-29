package com.example.pertemuan12_71190410

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import org.json.JSONObject

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val editKota = findViewById<EditText>(R.id.editKota)
        val btnCek = findViewById<Button>(R.id.btnCek)

        btnCek.setOnClickListener {
            cekCuaca(editKota.text.toString())
        }
    }

    fun cekCuaca(kota: String){
        val queue = Volley.newRequestQueue(this)
        val url = "https://api.openweathermap.org/data/2.5/weather?q=${kota}&appid=14a563dd4d6988f8d77d78eb2fd13e4f"

        val request = StringRequest(
            Request.Method.GET,
            url,
            Response.Listener<String> { response ->
                val data = JSONObject(response)
                val cuaca = data.getJSONArray("weather").getJSONObject(0).getString("description")
                val suhu = data.getJSONObject("main").getDouble("temp")
                val txtHasil = findViewById<TextView>(R.id.txtHasil)
                txtHasil.text = "${cuaca} (${String.format("%.2f",suhu-273.15).toDouble()}\u2103)"
            },
            Response.ErrorListener {

            })

        queue.add(request)
    }
}