package com.example.core.presentation

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.core.databinding.ItemMovieBinding
import com.example.core.domain.model.NewMoviesModel
import com.example.core.utils.CoreConstant.MOVIE_POSTER_URL

class HomeAdapter :
    ListAdapter<NewMoviesModel, HomeAdapter.HomeViewHolder>(DIFF_CALLBACK) {

    private lateinit var setOnClickCallback: OnClickCallback

    fun setOnClickCallback(onUpdatelickCallback: OnClickCallback) {
        this.setOnClickCallback = onUpdatelickCallback
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeViewHolder {
        val binding =
            ItemMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return HomeViewHolder(binding)
    }

    override fun onBindViewHolder(holder: HomeViewHolder, position: Int) {
        val movies = getItem(position)
        if (movies != null) {
            holder.bind(movies, holder.itemView.context)

            ItemMovieBinding.bind(holder.itemView).apply {
                this.sivMoviePoster.setOnClickListener {
                    setOnClickCallback.onItemClicked(movies, holder.itemView.context)
                }
            }
//            holder.itemView.findViewById<ImageView>(R.id.siv_movie_poster).setOnClickListener {
//                setOnClickCallback.onItemClicked(movies, holder.itemView.context)
//            }
        }
    }

    interface OnClickCallback {
        fun onItemClicked(movie: NewMoviesModel, context: Context)
    }


    class HomeViewHolder(private val binding: ItemMovieBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(data: NewMoviesModel, context: Context) {
            Glide
                .with(context)
                .load(MOVIE_POSTER_URL + data.posterPath)
                .into(binding.sivMoviePoster)
        }
    }

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<NewMoviesModel>() {
            override fun areItemsTheSame(oldItem: NewMoviesModel, newItem: NewMoviesModel): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: NewMoviesModel, newItem: NewMoviesModel): Boolean {
                return oldItem.id == newItem.id
            }
        }
    }
}