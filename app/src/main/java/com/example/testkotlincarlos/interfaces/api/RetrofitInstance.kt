package com.example.testkotlincarlos.interfaces.api

import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitInstance {
    companion object{
       private const val BaseUrl= "https://api.themoviedb.org/3/movie/"
        fun getRetrofitInstance():Retrofit{
            return Retrofit.Builder()
                    .baseUrl(BaseUrl)
                    .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
                    .build()
        }
    }
}