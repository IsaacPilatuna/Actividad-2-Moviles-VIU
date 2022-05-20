package com.example.actividad_2_moviles_viu.adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.actividad_2_moviles_viu.MovieItemDetail
import com.example.actividad_2_moviles_viu.R
import com.example.actividad_2_moviles_viu.holders.MovieViewHolder
import com.example.actividad_2_moviles_viu.models.Movie

class MovieAdapter (private val movies: List<Movie>) : RecyclerView.Adapter<MovieViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return MovieViewHolder(layoutInflater.inflate(R.layout.movie_item, parent, false))
    }

    override fun getItemCount(): Int = movies.size
    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val item = movies[position]
        holder.bind(item)
        holder.itemView.setOnClickListener{openDetail(it, item)}
    }

    private fun  openDetail(view: View, movie:Movie){
        val intent = Intent(view.context, MovieItemDetail::class.java)
        intent.putExtra("movie", movie)
        view.context.startActivity(intent)

    }
}