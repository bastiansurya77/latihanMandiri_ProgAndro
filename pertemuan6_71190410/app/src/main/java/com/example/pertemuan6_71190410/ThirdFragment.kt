package com.example.pertemuan6_71190410

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment

class ThirdFragment: Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val v3 = inflater.inflate(R.layout.fragment_third, container, false)
        val btnPindah3 = v3.findViewById<Button>(R.id.btnPindah3)
        btnPindah3.setOnClickListener {
            val i= Intent(activity, MainActivity::class.java)
            startActivity(i)
        }
        return v3
    }
}