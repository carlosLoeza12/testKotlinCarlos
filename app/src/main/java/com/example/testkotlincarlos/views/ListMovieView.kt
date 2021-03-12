package com.example.testkotlincarlos.views

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.testkotlincarlos.R
import com.example.testkotlincarlos.adapters.ListMovieAdapter
import com.example.testkotlincarlos.databinding.ActivityListMovieBinding
import com.example.testkotlincarlos.entieties.ListMovieEntity
import com.example.testkotlincarlos.interfaces.ListMovieInterface
import com.example.testkotlincarlos.presenters.ListMoviePresenter


class ListMovieView : AppCompatActivity(),ListMovieInterface.View, ListMovieAdapter.OnItemClickListener {

    private lateinit var binding: ActivityListMovieBinding
    private lateinit var adapter: ListMovieAdapter
    private lateinit var listMovieView: List<ListMovieEntity>

    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_main)
        binding = ActivityListMovieBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initializeElements()
    }
    private fun initializeElements(){
        val presenter: ListMovieInterface.Presenter = ListMoviePresenter(this, applicationContext)
        presenter.getListMoviePresenter()

        adapter = ListMovieAdapter(this,applicationContext)
        binding.recyclerMovies.layoutManager = GridLayoutManager(this@ListMovieView, 2)
        binding.recyclerMovies.setHasFixedSize(true)
        binding.recyclerMovies.adapter= adapter

        binding.swipeRefresh.setOnRefreshListener {
            presenter.getListMoviePresenter()
            binding.swipeRefresh.isRefreshing = false
        }
    }

    override fun messageView(menssage: String) {
        Toast.makeText(applicationContext,menssage,Toast.LENGTH_SHORT).show()
    }

    override fun loadDataView(listMovie: List<ListMovieEntity>) {
        listMovieView = listMovie
        //adapter = ListMovieAdapter(listMovie, this,applicationContext)
        adapter.sendList(listMovie)

    }

    override fun onItemClick(position: Int) {
        val intent = Intent(this, DetailMovieView::class.java)
        intent.putExtra("idMovie",listMovieView[position].id)
        intent.putExtra("titleMovie",listMovieView[position].title)
        intent.putExtra("voteMovie",listMovieView[position].vote_average)
        intent.putExtra("dateReleaseMovie",listMovieView[position].release_date)
        intent.putExtra("descriptionMovie",listMovieView[position].overview)
        intent.putExtra("urlPhoto", getString(R.string.baseConfiguration)+listMovieView[position].poster_path)
        startActivity(intent)
    }
}