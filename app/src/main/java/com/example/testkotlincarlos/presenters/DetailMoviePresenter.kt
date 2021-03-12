package com.example.testkotlincarlos.presenters

import android.content.Context
import com.example.testkotlincarlos.interactors.DetailMovieInteractor
import com.example.testkotlincarlos.interfaces.DetailMovieInterface
import com.example.testkotlincarlos.views.DetailMovieView

class DetailMoviePresenter(private val view: DetailMovieView,
                           private val context: Context) : DetailMovieInterface.Presenter, DetailMovieInterface.Listener {

    private val interactor: DetailMovieInterface.Interactor = DetailMovieInteractor(context)

    override fun getDetailMoviePresenter(idMovie: String) {
        interactor.getDetailMovieInteractor(idMovie, this)
    }

    override fun messageListener(menssage: String) {
        view.messageView(menssage)
    }

    override fun loadNameGenreListener(nameGenre: String) {
        view.loadNameGenreView(nameGenre)
    }

    override fun loadDurationListener(duration: String) {
        view.loadDurationView(duration)
    }
}