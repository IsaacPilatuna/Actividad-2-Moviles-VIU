package com.example.actividad_2_moviles_viu.holders

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.actividad_2_moviles_viu.databinding.MovieItemBinding
import com.example.actividad_2_moviles_viu.models.Movie
import com.squareup.picasso.Picasso

class MovieViewHolder (view: View): RecyclerView.ViewHolder(view) {
    private val binding = MovieItemBinding.bind(view)
    fun bind(movie: Movie){
        binding.movieTitleTxt.text = "${movie.name} (${movie.release})"
        binding.movieDescriptionTxt.text = movie.description
        binding.moviePlaytimeTxt.text = movie.playtime
        Picasso.get().load(movie.poster).into(binding.movieImg)
    }
}