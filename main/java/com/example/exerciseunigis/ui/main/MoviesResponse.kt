package com.example.exerciseunigis.ui.main

data class MoviesResponse(
    val results: List<Movie>
)

data class Movie(
    val id: Int,
    val title: String,
    val overview: String,
    val poster_path: String
)
