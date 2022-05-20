package com.example.actividad_2_moviles_viu

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.actividad_2_moviles_viu.databinding.ActivityMainBinding
import com.example.actividad_2_moviles_viu.databinding.MovieItemDetailBinding
import com.example.actividad_2_moviles_viu.models.Movie
import com.example.actividad_2_moviles_viu.services.AppService
import com.squareup.picasso.Picasso

class MovieItemDetail : AppCompatActivity() {
    private lateinit var binding: MovieItemDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = MovieItemDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        val movie = intent.getSerializableExtra("movie") as Movie
        mapMovieData(movie)
        binding.btnDeleteMovie.setOnClickListener{deleteMovie(movie)}
    }

    private fun mapMovieData(movie:Movie){
        binding.txtMovieDetailDescription.text = movie.description
        binding.txtMovieDetailPlaytime.text = movie.playtime
        binding.txtMovieDetailRelease.text = movie.release
        binding.txtMovieDetailPlot.text = movie.plot
        binding.txtMovieDetailTitle.text = movie.name
        Picasso.get().load(movie.poster).into(binding.imgMovieDetail)
    }

    private fun deleteMovie(movie:Movie){
        val builder = AlertDialog.Builder(this)
        builder.setMessage("Are you sure you want to Delete the movie ${movie.name}?")
            .setCancelable(false)
            .setPositiveButton("Yes") { dialog, id ->
                AppService.deleteMovie(movie.id, this)
                this.onBackPressed()
            }
            .setNegativeButton("No") { dialog, id ->
                dialog.dismiss()
            }
        val alert = builder.create()
        alert.show()
    }
}