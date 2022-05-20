package com.example.actividad_2_moviles_viu.services

import android.app.Activity
import android.widget.Toast
import com.example.actividad_2_moviles_viu.adapters.MovieAdapter
import com.example.actividad_2_moviles_viu.models.Movie
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import javax.inject.Inject

class AppService @Inject constructor() {
    companion object{
        private val movies = mutableListOf<Movie>()

        fun setMovies(movies:MutableList<Movie>, activity:Activity){
            this.movies.clear()
            this.movies.addAll(movies)
            saveState(activity)
        }

        fun deleteMovie(id:Int, activity: Activity){
            val index = movies.indexOfFirst { it.id==id }
            this.movies.removeAt(index)
            saveState(activity)
            Toast.makeText(activity, "The movie was deleted", Toast.LENGTH_SHORT).show()
        }

        fun getStoredMovies(activity: Activity):MutableList<Movie>{
            return try{
                val moviesStr = SharedPreferences.getPreferenceString(activity, "movies")
                val itemType = object : TypeToken<MutableList<Movie>>() {}.type
                Gson().fromJson(moviesStr, itemType)
            }catch (e:Exception){
                mutableListOf()
            }
        }

        private fun saveState(activity: Activity){
            val gson = Gson()
            val serializedMovies = gson.toJson(movies)
            SharedPreferences.storePreferenceString(activity, serializedMovies,"movies")
        }

    }
}