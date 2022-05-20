package com.example.actividad_2_moviles_viu

import android.app.Activity
import android.content.Intent
import android.opengl.Visibility
import android.os.Bundle
import android.os.PersistableBundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.actividad_2_moviles_viu.adapters.MovieAdapter
import com.example.actividad_2_moviles_viu.services.SharedPreferences
import com.example.actividad_2_moviles_viu.databinding.ActivityMainBinding
import com.example.actividad_2_moviles_viu.interfaces.HttpService
import com.example.actividad_2_moviles_viu.models.Movie
import com.example.actividad_2_moviles_viu.services.AppService
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import dagger.hilt.android.AndroidEntryPoint
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var appService: AppService
    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: MovieAdapter
    private val movies = mutableListOf<Movie>()

    object AppConstants{
        const val BASE_URL:String = "https://gist.githubusercontent.com/"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initRecyclerView()
        binding.refreshBtn.setOnClickListener(getMovies)
    }

    override fun onResume() {
        super.onResume()
        getStoredMovies()
    }

    private val getMovies: View.OnClickListener= View.OnClickListener {
        val retrofit = getRetrofit()
        val service = retrofit.create(HttpService::class.java)
        val result = service.getMovies()
        binding.progressBar.visibility = View.VISIBLE;
        result.enqueue(object:Callback<MutableList<Movie>>{
            override fun onResponse(
                call: Call<MutableList<Movie>>,
                response: Response<MutableList<Movie>>
            ) {
                val moviesResponse = response.body()!!
                appService.setMovies(moviesResponse, this@MainActivity)
                getStoredMovies()
                binding.progressBar.visibility = View.INVISIBLE;
            }

            override fun onFailure(call: Call<MutableList<Movie>>, t: Throwable) {
                showErrorMessage()
                binding.progressBar.visibility = View.INVISIBLE;
            }

        })
    }


    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        try{
            getStoredMovies()
        }catch (e:Exception){}
    }


    private fun showErrorMessage(){
        Toast.makeText(this, "Error occurred during the request!", Toast.LENGTH_SHORT).show()
    }

    private fun getRetrofit():Retrofit{
        return Retrofit.Builder()
            .baseUrl(AppConstants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    private fun initRecyclerView(){
        adapter = MovieAdapter(movies)
        binding.moviesRecyclerView.layoutManager = LinearLayoutManager(this)
        binding.moviesRecyclerView.adapter = adapter
    }

    private fun getStoredMovies(){
        movies.clear()
        val storedMovies = appService.getStoredMovies(this)
        appService.setMovies(storedMovies, this)
        movies.addAll(storedMovies)
        adapter.notifyDataSetChanged()

    }
}