package com.example.testkotlincarlos.interfaces.api

import com.example.testkotlincarlos.entieties.DetailMovieResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface DetailMovieApiInterface {

    @GET("{id}")
    suspend fun getDetailMovieById(@Path("id") id: String,
                           @Query("api_key") apikey: String,
                           @Query("language") languaje: String): Response<DetailMovieResponse>
}