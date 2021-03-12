package com.example.testkotlincarlos.interfaces

import com.example.testkotlincarlos.entieties.DetailMovieResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface DetailMovieApiInterface {

    @GET("{id}")
    fun getDetailMovieById(@Path("id") id: String,
                           @Query("api_key") apikey: String,
                           @Query("language") languaje: String): Call<DetailMovieResponse>
}