package com.example.syngrychallenge.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Button
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.syngrychallenge.R
import com.example.syngrychallenge.databinding.AlphabetItemBinding
import com.example.syngrychallenge.model.WordModel

class WordAdapter :
    ListAdapter<WordModel, WordAdapter.MyViewHolder>(DIFF_CALLBACK) {

    private lateinit var onItemClickCallback: OnItemClickCallback

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding =
            AlphabetItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val wordList = getItem(position)
        if (wordList != null) {
            holder.bind(wordList)

            holder.itemView.findViewById<Button>(R.id.button).setOnClickListener{
                onItemClickCallback.onItemClicked(wordList, holder.itemView.context)
            }
        }
    }

    interface OnItemClickCallback {
        fun onItemClicked(item: WordModel, context: Context)
    }

    class MyViewHolder(private val binding: AlphabetItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: WordModel) {
            val btn = binding.button
            btn.text = item.word
        }
    }

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<WordModel>() {
            override fun areItemsTheSame(oldItem: WordModel, newItem: WordModel): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: WordModel, newItem: WordModel): Boolean {
                return oldItem.word == newItem.word
            }
        }
    }
}
