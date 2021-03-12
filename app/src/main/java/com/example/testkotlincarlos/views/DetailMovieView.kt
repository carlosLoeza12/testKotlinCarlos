package com.example.testkotlincarlos.views

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import coil.load
import com.example.testkotlincarlos.R
import com.example.testkotlincarlos.databinding.ActivityDetailMoviewBinding
import com.example.testkotlincarlos.interfaces.DetailMovieInterface
import com.example.testkotlincarlos.presenters.DetailMoviePresenter

class DetailMovieView : AppCompatActivity(), DetailMovieInterface.View {

    private lateinit var binding: ActivityDetailMoviewBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailMoviewBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initializeElements()
    }

    private fun initializeElements() {
        val bundleDataView: Bundle? = this.intent.extras
        val presenter: DetailMovieInterface.Presenter = DetailMoviePresenter(this, applicationContext)

        bundleDataView?.let {
            binding.txtTitle.text = bundleDataView.getString("titleMovie")
            binding.txtDataVote.text = bundleDataView.getString("voteMovie")
            binding.txtDataDateRelease.text = bundleDataView.getString("dateReleaseMovie")
            binding.txtDataDescription.text = bundleDataView.getString("descriptionMovie")

            binding.imgPhotoMovie.load(bundleDataView.getString("urlPhoto"))
            bundleDataView.getString("idMovie")?.let { presenter.getDetailMoviePresenter(it) }
        }
    }

    override fun loadNameGenreView(nameGenre: String) {
        binding.txtDataGenre.text = nameGenre
    }

    override fun loadDurationView(duration: String) {
        binding.txtDataDuration.text = duration
    }

    override fun messageView(menssage: String) {
        Toast.makeText(applicationContext, menssage, Toast.LENGTH_LONG).show()
    }
}