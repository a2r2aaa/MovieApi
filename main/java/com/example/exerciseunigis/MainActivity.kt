package com.example.exerciseunigis

import android.os.AsyncTask
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.ListView

import okhttp3.OkHttpClient
import okhttp3.Request
import org.json.JSONArray
import org.json.JSONObject
import com.example.exerciseunigis.ui.main.ApiClient
import com.example.exerciseunigis.ui.main.Movie
import com.example.exerciseunigis.ui.main.MovieAdapter

import com.example.exerciseunigis.ui.main.MoviesResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    private lateinit var listView: ListView
    private lateinit var adapter: MovieAdapter
    override fun onCreate(savedInstanceState: Bundle?) {

         fun getMovies() {
            val client = OkHttpClient()
            val request = Request.Builder()
                .url("https://api.themoviedb.org/3/movie/now_playing?language=en-US&page=undefined&api_key=55957fcf3ba81b137f8fc01ac5a31fb5")
                .build()

            AsyncTask.execute {
                client.newCall(request).execute().use { response ->
                    val json = JSONObject(response.body()!!.string())
                    val results = json.getJSONArray("results")
                    for (i in 0 until results.length()) {
                        val movieJson = results.getJSONObject(i)
                        val title = movieJson.getString("title")
                        val overview = movieJson.getString("overview")
                        val movie = Movie(888, title, overview, "/fiVW06jE7z9YnO4trhaMEdclSiC.jpg")
                        runOnUiThread { adapter.add(movie) }
                    }
                }
            }
        }


        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        listView = findViewById(R.id.listView)
        adapter = MovieAdapter(this, ArrayList())
        listView.adapter = adapter

      getMovies()


        val apiKey = "55957fcf3ba81b137f8fc01ac5a31fb5"
        val call = ApiClient.tmdbService.getNowPlaying(apiKey, "en-US", 1)
        call.enqueue(object : Callback<MoviesResponse> {
            override fun onResponse(call: Call<MoviesResponse>, response: Response<MoviesResponse>) {
                val movies = response.body()?.results

                if (movies != null) {
                    println("movies"+movies)
                }
                // Use the movies list
            }








//        recyclerView.layoutManager = LinearLayoutManager(this)
//
//            val apiKey = "55957fcf3ba81b137f8fc01ac5a31fb5"
//            val call = ApiClient.tmdbService.getNowPlaying(apiKey, "en-US", 1)
//            call.enqueue(object : Callback<MoviesResponse> {
//                override fun onResponse(call: Call<MoviesResponse>, response: Response<MoviesResponse>) {
//                    val movies = response.body()?.results ?: emptyList()
//                    recyclerView.adapter = MoviesAdapter(movies)
//                }



            override fun onFailure(call: Call<MoviesResponse>, t: Throwable) {
                // Handle error
            }
        })
    }
}