package com.example.testkotlincarlos.views
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.testkotlincarlos.databinding.ActivityDetailMoviewBinding
import com.example.testkotlincarlos.interfaces.DetailMovieInterface
import com.example.testkotlincarlos.interactors.loadUrl

import com.example.testkotlincarlos.presenters.DetailMoviePresenter
import com.example.testkotlincarlos.interactors.setTextBundle
import com.example.testkotlincarlos.interactors.toast

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
        val actionbar = supportActionBar
        actionbar?.setDisplayHomeAsUpEnabled(true)

        bundleDataView?.let {
            bundleDataView.getString("idMovie")?.let { presenter.getDetailMoviePresenter(it) }
            bundleDataView.getString("urlPhoto")?.let { binding.imgPhotoMovie.loadUrl(it) }

            binding.txtTitle.setTextBundle(bundleDataView, "titleMovie")
            binding.txtDataVote.setTextBundle(bundleDataView, "voteMovie")
            binding.txtDataDateRelease.setTextBundle(bundleDataView, "dateReleaseMovie")
            binding.txtDataDescription.setTextBundle(bundleDataView, "descriptionMovie")
            /*binding.txtTitle.text = bundleDataView.getString("titleMovie")
            binding.txtDataVote.text = bundleDataView.getString("voteMovie")
            binding.txtDataDateRelease.text = bundleDataView.getString("dateReleaseMovie")
            binding.txtDataDescription.text = bundleDataView.getString("descriptionMovie")
            */
        }
    }

    override fun loadNameGenreView(nameGenre: String) {
        binding.txtDataGenre.text = nameGenre
    }

    override fun loadDurationView(duration: String) {
        binding.txtDataDuration.text = duration
    }

    override fun messageView(menssage: String) {
        toast(menssage)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}