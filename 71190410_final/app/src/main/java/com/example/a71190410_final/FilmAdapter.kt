package com.example.a71190410_final

import android.app.Activity
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.firestore.FirebaseFirestore

class FilmAdapter (val listFilm: ArrayList<Film>): RecyclerView.Adapter<FilmAdapter.FilmHolder>() {
    class FilmHolder(val view: View) : RecyclerView.ViewHolder(view) {
        var fstore: FirebaseFirestore? = null
        var film: Film? = null


        fun bind(film: Film) {
            fstore = FirebaseFirestore.getInstance()
            this.film = film

            view.findViewById<TextView>(R.id.textFilm).text = film.judul
            view.findViewById<TextView>(R.id.textGenre).text = film.genre
            view.findViewById<TextView>(R.id.textNamaProduser).text = film.namaProduser
            view.findViewById<TextView>(R.id.textPemeran1).text = film.pemeran1
            view.findViewById<TextView>(R.id.textPemeran2).text = film.pemeran2
            view.findViewById<TextView>(R.id.textPemeran3).text = film.pemeran3
            view.findViewById<TextView>(R.id.textTahunRilis).text = film.thnRilis
            view.findViewById<Button>(R.id.btnEdit).setOnClickListener {
                val i : Intent = Intent(view.context, EditFilm::class.java)

                i.putExtra("judul", film.judul)
                i.putExtra("genre", film.genre)
                i.putExtra("namaProduser", film.namaProduser)
                i.putExtra("pemeran1", film.pemeran1)
                i.putExtra("pemeran2", film.pemeran2)
                i.putExtra("pemeran3", film.pemeran3)
                i.putExtra("thnRilis", film.thnRilis)

                view.context.startActivity(i)
            }
            view.findViewById<Button>(R.id.btnDel).setOnClickListener {

                fstore!!.collection("film").whereEqualTo("judul", film.judul).get()
                    .addOnSuccessListener {
                        for (doc in it){
                            fstore!!.collection("film").document(doc.id)
                                .delete()
                                .addOnSuccessListener {
                                    Toast.makeText(view.context, "Film berhasil dihapus!", Toast.LENGTH_SHORT).show()
                                    (view.context as Activity).recreate()
                                }
                                .addOnFailureListener{
                                    Toast.makeText(view.context, "Film gagal dihapus!", Toast.LENGTH_SHORT).show()
                                }
                        }
                    }

            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FilmHolder {
        val fholder = LayoutInflater.from(parent.context).inflate(R.layout.activity_item, parent, false)
        return FilmHolder(fholder)
    }

    override fun onBindViewHolder(holder: FilmHolder, position: Int) {
        holder.bind(listFilm[position])
    }

    override fun getItemCount(): Int {
        return listFilm.size
    }
}