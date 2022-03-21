package com.example.pertemuan6_71190410

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment

class SecondFragment: Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val v2 = inflater.inflate(R.layout.fragment_second, container, false)
        val btnPindah2 = v2.findViewById<Button>(R.id.btnPindah2)
        btnPindah2.setOnClickListener {
            val i= Intent(activity, ThirdActivity::class.java)
            startActivity(i)
        }
        return v2
    }
}