package com.example.core.presentation

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.core.databinding.ItemMovieBinding
import com.example.core.domain.model.MovieModel
import com.example.core.utils.CoreConstant.MOVIE_POSTER_URL

class MoviesAdapter :
    ListAdapter<MovieModel, MoviesAdapter.HomeViewHolder>(DIFF_CALLBACK) {

    private lateinit var setOnClickCallback: OnClickCallback

    fun setOnClickCallback(onUpdateClickCallback: OnClickCallback) {
        this.setOnClickCallback = onUpdateClickCallback
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeViewHolder {
        val binding =
            ItemMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return HomeViewHolder(binding)
    }

    override fun onBindViewHolder(holder: HomeViewHolder, position: Int) {
        Log.d("homeAdapter", itemCount.toString())

        val movies = getItem(position)
        if (movies != null) {
            holder.bind(movies, holder.itemView.context)

            ItemMovieBinding.bind(holder.itemView).apply {
                this.sivMoviePoster.setOnClickListener {
                    setOnClickCallback.onItemClicked(movies)
                }
            }
//            holder.itemView.findViewById<ImageView>(R.id.siv_movie_poster).setOnClickListener {
//                setOnClickCallback.onItemClicked(movies, holder.itemView.context)
//            }
        }
    }

    interface OnClickCallback {
        fun onItemClicked(movie: MovieModel)
    }


    class HomeViewHolder(private val binding: ItemMovieBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(data: MovieModel, context: Context) {
            Glide
                .with(context)
                .load(MOVIE_POSTER_URL + data.posterPath)
                .into(binding.sivMoviePoster)
        }
    }

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<MovieModel>() {
            override fun areItemsTheSame(oldItem: MovieModel, newItem: MovieModel): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: MovieModel, newItem: MovieModel): Boolean {
                return oldItem.id == newItem.id
            }
        }
    }
}