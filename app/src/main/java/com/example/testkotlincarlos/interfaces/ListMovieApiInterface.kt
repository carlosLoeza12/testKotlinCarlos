package com.example.testkotlincarlos.interfaces

import com.example.testkotlincarlos.entieties.ListMovieResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ListMovieApiInterface {
    @GET("now_playing?")
    fun fetchMovies(@Query("api_key") apikey: String,
                    @Query("language") languaje: String,
                    @Query("page") page: String): Call<ListMovieResponse>

}