package com.example.actividad_2_moviles_viu.interfaces

import com.example.actividad_2_moviles_viu.models.Movie
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Url

interface HttpService {
    @GET("skydoves/176c209dbce4a53c0ff9589e07255f30/raw/6489d9712702e093c4df71500fb822f0d408ef75/DisneyPosters2.json")
    fun getMovies(): Call<MutableList<Movie>>
}