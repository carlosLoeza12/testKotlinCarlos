package com.example.testkotlincarlos.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.testkotlincarlos.R
import com.example.testkotlincarlos.databinding.ItemMovieBinding
import com.example.testkotlincarlos.entieties.ListMovieEntity

class ListMovieAdapter(
    private val listener: OnItemClickListener,
    private val context: Context) : RecyclerView.Adapter<ListMovieAdapter.ViewHolder>()  {

    private  var listMovie: List<ListMovieEntity>? = null
    private  var flagClick=false;

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

            holder.binding.imgPhotoMovie.load(context.getString(R.string.baseConfiguration) + movie.poster_path)
        }
    }

    override fun getItemCount(): Int {
        listMovie?.let {
            return listMovie!!.size
        } ?: run {
            return 0
        }
    }


    inner class ViewHolder(view: View): RecyclerView.ViewHolder(view),View.OnClickListener {

        val binding = ItemMovieBinding.bind(view)
        init {
            view.setOnClickListener(this)
        }

        override fun onClick(v: View) {
            val position: Int = adapterPosition
            if (position != RecyclerView.NO_POSITION&&!flagClick) {
                listener.onItemClick(position, v)
                //flagClick = true
            }
        }
    }

    interface OnItemClickListener{
        fun onItemClick(position: Int, view : View) {

        }
    }
}