package com.example.testkotlincarlos.adapters
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.testkotlincarlos.R
import com.example.testkotlincarlos.databinding.ItemMovieBinding
import com.example.testkotlincarlos.entieties.ListMovieEntity
import com.example.testkotlincarlos.interactors.loadUrl
import com.example.testkotlincarlos.views.DetailMovieView

class ListMovieAdapter(private val context: Context) : RecyclerView.Adapter<ListMovieAdapter.ViewHolder>() {

    private var listMovie: List<ListMovieEntity>? = null

    fun sendList(movies: List<ListMovieEntity>) {
        listMovie = movies
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return ViewHolder(layoutInflater.inflate(R.layout.item_movie, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val movie = listMovie?.get(position)
        movie?.let {
            holder.binding.txtTitleMovie.text = movie.title
            holder.binding.txtDateReleaseMovie.text = movie.release_date
            holder.binding.txtVoteMovie.text = movie.vote_average
            holder.binding.imgPhotoMovie.loadUrl(context.getString(R.string.base_configuration) + movie.poster_path)
        }
    }

    override fun getItemCount(): Int {
        listMovie?.let {
            return listMovie!!.size
        } ?: run {
            return 0
        }
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val binding = ItemMovieBinding.bind(view)

        init {
            view.setOnClickListener {
                val position: Int = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    val intent = Intent(context, DetailMovieView::class.java)
                    intent.putExtra("idMovie", listMovie?.get(position)?.id)
                    intent.putExtra("titleMovie", listMovie?.get(position)?.title)
                    intent.putExtra("voteMovie", listMovie?.get(position)?.vote_average)
                    intent.putExtra("dateReleaseMovie", listMovie?.get(position)?.release_date)
                    intent.putExtra("descriptionMovie", listMovie?.get(position)?.overview)
                    intent.putExtra("urlPhoto", context.getString(R.string.base_configuration) + listMovie?.get(position)?.poster_path
                    )
                    context.startActivity(intent)
                }
            }
        }
    }
}