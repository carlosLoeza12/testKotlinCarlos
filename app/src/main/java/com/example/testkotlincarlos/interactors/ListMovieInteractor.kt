package com.example.testkotlincarlos.interactors
import android.content.Context

import android.util.Log
import com.example.testkotlincarlos.R
import com.example.testkotlincarlos.entieties.ListMovieResponse
import com.example.testkotlincarlos.interfaces.ListMovieInterface
import com.example.testkotlincarlos.interfaces.ListMovieApiInterface
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ListMovieInteractor(val context: Context) :ListMovieInterface.Interactor {

    override fun getListMovieInteractor(listener: ListMovieInterface.Listener) {
        //hacer coorrtutina
        CoroutineScope(Dispatchers.IO).launch {
            //lo que este dentro se va hacer en un hilo secundario
            val retrofit = Retrofit.Builder()
                    .baseUrl(context.getString(R.string.api_base_movie))
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()

            val api = retrofit.create(ListMovieApiInterface::class.java)
            api.fetchMovies(context.getString(R.string.apikeyMovie),"es-MX","1").enqueue(object : Callback<ListMovieResponse> {
                override fun onResponse(call: Call<ListMovieResponse>, response: Response<ListMovieResponse>) {
                    Log.e("response", "response ${response.body()}")
                    val movies = response.body()?.results
                    movies?.let {
                        listener.loadDataListener(movies)
                    }
                }
                override fun onFailure(call: Call<ListMovieResponse>, t: Throwable) {
                    listener.messageListener(context.getString(R.string.error_connection))
                }
            })
        }
    }
}