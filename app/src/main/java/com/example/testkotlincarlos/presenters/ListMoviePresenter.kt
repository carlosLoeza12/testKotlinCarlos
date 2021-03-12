package com.example.testkotlincarlos.presenters

import android.content.Context
import com.example.testkotlincarlos.entieties.ListMovieEntity
import com.example.testkotlincarlos.interactors.ListMovieInteractor
import com.example.testkotlincarlos.interfaces.ListMovieInterface
import com.example.testkotlincarlos.views.ListMovieView

class ListMoviePresenter(private var view: ListMovieView, private var context: Context) : ListMovieInterface.Presenter,ListMovieInterface.Listener {

    private val interactor : ListMovieInterface.Interactor = ListMovieInteractor(context)

    override fun getListMoviePresenter() {
        interactor.getListMovieInteractor(this)
    }

    override fun messageListener(menssage: String) {
        view.messageView(menssage)
    }

    override fun loadDataListener(listMovie: List<ListMovieEntity>) {
        view.loadDataView(listMovie)
    }

}