package com.example.testkotlincarlos.interfaces

import com.example.testkotlincarlos.entieties.MovieGenreEntity


interface DetailMovieInterface {

    interface View {
        fun loadNameGenreView(nameGenre: String)
        fun loadDurationView(duration: String)
        fun messageView(menssage: String)
    }

    interface Presenter {
        fun getDetailMoviePresenter(idMovie: String)
    }

    interface Interactor {
        fun getDetailMovieInteractor(idMovie: String, Listener: Listener)
        fun getNameGenreInteractor(ListNameGenre: List<MovieGenreEntity>, Listener: Listener)
    }

    interface Listener {
        fun messageListener(menssage: String)
        fun loadNameGenreListener(nameGenre: String)
        fun loadDurationListener(duration: String)
    }

}