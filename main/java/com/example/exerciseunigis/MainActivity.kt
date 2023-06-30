package com.example.exerciseunigis

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.exerciseunigis.ui.main.ApiClient
import com.example.exerciseunigis.ui.main.MoviesAdapter
import com.example.exerciseunigis.ui.main.MoviesResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        val apiKey = "55957fcf3ba81b137f8fc01ac5a31fb5"
//        val call = ApiClient.tmdbService.getNowPlaying(apiKey, "en-US", 1)
//        call.enqueue(object : Callback<MoviesResponse> {
//            override fun onResponse(call: Call<MoviesResponse>, response: Response<MoviesResponse>) {
//                val movies = response.body()?.results
//
//                if (movies != null) {
//                    println("movies"+movies)
//                }
//                // Use the movies list
//            }






        recyclerView.layoutManager = LinearLayoutManager(this)

            val apiKey = "55957fcf3ba81b137f8fc01ac5a31fb5"
            val call = ApiClient.tmdbService.getNowPlaying(apiKey, "en-US", 1)
            call.enqueue(object : Callback<MoviesResponse> {
                override fun onResponse(call: Call<MoviesResponse>, response: Response<MoviesResponse>) {
                    val movies = response.body()?.results ?: emptyList()
                    recyclerView.adapter = MoviesAdapter(movies)
                }



            override fun onFailure(call: Call<MoviesResponse>, t: Throwable) {
                // Handle error
            }
        })
    }
}