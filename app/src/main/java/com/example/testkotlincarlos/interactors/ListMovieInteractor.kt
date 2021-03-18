package com.example.testkotlincarlos.interactors

import android.content.Context
import android.util.Log
import com.example.testkotlincarlos.R
import com.example.testkotlincarlos.interfaces.ListMovieInterface
import com.example.testkotlincarlos.interfaces.api.ListMovieApiInterface
import com.example.testkotlincarlos.interfaces.api.RetrofitInstance
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.lang.Exception

class ListMovieInteractor(private val context: Context) : ListMovieInterface.Interactor {

    override fun getListMovieInteractor(listener: ListMovieInterface.Listener) {

        val retService = RetrofitInstance
                .getRetrofitInstance()
                .create(ListMovieApiInterface::class.java)

        //coorrutina
        CoroutineScope(Dispatchers.Main).launch {
            try {
                val response = withContext(Dispatchers.IO) {
                    retService.fetchMovies(context.getString(R.string.api_key_Movie), "es-MX", "1")
                }

                if (response.isSuccessful) {
                    val movies = response.body()?.results
                    Log.e("response", "response ${response.body()}")
                    movies?.let {
                        listener.loadDataListener(it)
                    }
                }else{
                    listener.messageListener(context.getString(R.string.error))
                }
            } catch (e: Exception) {
                listener.messageListener(context.getString(R.string.error_connection))
            }
        }
    }
}