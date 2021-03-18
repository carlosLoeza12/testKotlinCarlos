package com.example.testkotlincarlos.interactors

import android.content.Context
import android.util.Log
import com.example.testkotlincarlos.R
import com.example.testkotlincarlos.entieties.MovieGenreEntity
import com.example.testkotlincarlos.interfaces.api.DetailMovieApiInterface
import com.example.testkotlincarlos.interfaces.DetailMovieInterface
import com.example.testkotlincarlos.interfaces.api.RetrofitInstance
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class DetailMovieInteractor(private val context: Context) : DetailMovieInterface.Interactor {

    private var allGenreName: String =""

    override fun getDetailMovieInteractor(idMovie: String, Listener: DetailMovieInterface.Listener) {

        val retService = RetrofitInstance
                .getRetrofitInstance()
                .create(DetailMovieApiInterface::class.java)

        CoroutineScope(Dispatchers.Main).launch {
            try {
                val response = withContext(Dispatchers.IO) {
                    retService.getDetailMovieById(idMovie, context.getString(R.string.api_key_Movie), "es-MX")
                }
                if (response.isSuccessful) {
                    Log.e("response", "response ${response.body()}")
                    val runtime = response.body()?.runtime
                    val genres = response.body()?.genres
                    genres?.let {
                        getNameGenreInteractor(it, Listener)
                    }
                    runtime?.let {
                        Listener.loadDurationListener("$it min")
                    }
                } else {
                    Listener.messageListener(context.getString(R.string.error))
                }
            } catch (e: Exception) {
                Listener.messageListener(context.getString(R.string.error_connection))
            }
        }
    }

    override fun getNameGenreInteractor(ListNameGenre: List<MovieGenreEntity>, Listener: DetailMovieInterface.Listener) {
        for(nameGenre in ListNameGenre){
            allGenreName = "$allGenreName ${nameGenre.name}"
        }
        Listener.loadNameGenreListener(allGenreName)
        Log.e("genres",allGenreName)
    }
}