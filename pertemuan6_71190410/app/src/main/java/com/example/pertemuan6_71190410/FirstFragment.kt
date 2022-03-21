package com.example.pertemuan6_71190410

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment

class FirstFragment: Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val v1 = inflater.inflate(R.layout.fragment_first, container, false)
        val btnPindah1= v1.findViewById<Button>(R.id.btnPindah1)
        btnPindah1.setOnClickListener {
            val i= Intent(activity, SecondActivity::class.java)
            startActivity(i)
        }
        return v1
    }

}