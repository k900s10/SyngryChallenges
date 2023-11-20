package com.example.core.presentation

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.core.databinding.ItemCastMovieBinding
import com.example.core.domain.model.CastsModel
import com.example.core.utils.CoreConstant.MOVIE_POSTER_URL

class CastsAdapter :
    ListAdapter<CastsModel, CastsAdapter.CastsViewHolder>(DIFF_CALLBACK) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CastsViewHolder {
        val binding =
            ItemCastMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CastsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CastsViewHolder, position: Int) {
        val casts = getItem(position)
        if (casts != null) {
            holder.bind(casts, holder.itemView.context)
        }
    }

    class CastsViewHolder(private val binding: ItemCastMovieBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(cast: CastsModel, context: Context) {
            Glide
                .with(context)
                .load(MOVIE_POSTER_URL + cast.profilePath)
                .into(binding.sivMoviePoster)
            binding.tvOriginalName.text = cast.originalName
            binding.tvCharacter.text = cast.character
        }
    }

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<CastsModel>() {
            override fun areItemsTheSame(oldItem: CastsModel, newItem: CastsModel): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: CastsModel, newItem: CastsModel): Boolean {
                return oldItem.id == newItem.id
            }
        }
    }
}
