package com.example.testkotlincarlos.interfaces.api

import com.example.testkotlincarlos.entieties.ListMovieResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ListMovieApiInterface {
    @GET("now_playing?")
    suspend fun fetchMovies(@Query("api_key") apikey: String,
                    @Query("language") languaje: String,
                    @Query("page") page: String): Response<ListMovieResponse>

}