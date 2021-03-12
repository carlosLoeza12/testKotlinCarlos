package com.example.testkotlincarlos.interfaces

import com.example.testkotlincarlos.entieties.ListMovieEntity


interface ListMovieInterface {
    interface View {
        fun messageView(menssage: String)
        fun loadDataView(listMovie: List<ListMovieEntity>)
    }

    interface Presenter{
        fun getListMoviePresenter();
    }

    interface Interactor{
        fun getListMovieInteractor(listener: Listener)
    }

    interface Listener{
        fun messageListener(menssage: String)
        fun loadDataListener(listMovie: List<ListMovieEntity>)
    }
}