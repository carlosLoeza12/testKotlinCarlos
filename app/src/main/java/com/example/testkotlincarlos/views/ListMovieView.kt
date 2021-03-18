package com.example.testkotlincarlos.views
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.animation.AnimationUtils
import android.view.animation.LayoutAnimationController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.testkotlincarlos.R
import com.example.testkotlincarlos.adapters.ListMovieAdapter
import com.example.testkotlincarlos.interactors.configuration
import com.example.testkotlincarlos.databinding.ActivityListMovieBinding
import com.example.testkotlincarlos.entieties.ListMovieEntity
import com.example.testkotlincarlos.interfaces.ListMovieInterface
import com.example.testkotlincarlos.presenters.ListMoviePresenter
import com.example.testkotlincarlos.interactors.toast

class ListMovieView : AppCompatActivity(),ListMovieInterface.View {

    private lateinit var binding: ActivityListMovieBinding
    private lateinit var adapter: ListMovieAdapter
    private lateinit var listMovieView: List<ListMovieEntity>
    private lateinit var animationController: LayoutAnimationController

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

        adapter = ListMovieAdapter(this)
        animationController = AnimationUtils.loadLayoutAnimation(applicationContext, R.anim.layout_animation_top)
        /*binding.recyclerMovies.layoutManager = GridLayoutManager(this@ListMovieView, 2)
         binding.recyclerMovies.setHasFixedSize(true)
         binding.recyclerMovies.adapter= adapter*/
        binding.recyclerMovies.configuration(GridLayoutManager(this@ListMovieView, 2),adapter)

        binding.swipeRefresh.setOnRefreshListener {
            presenter.getListMoviePresenter()
            binding.swipeRefresh.isRefreshing = false
        }
    }

    override fun messageView(menssage: String) {
        toast(menssage)
    }


    override fun loadDataView(listMovie: List<ListMovieEntity>) {
        listMovieView = listMovie
        binding.recyclerMovies.layoutAnimation = animationController
        adapter.sendList(listMovie)
    }
}
