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
import com.example.syngrychallenge.model.AlphabetModel

class AlphabetAdapter :
    ListAdapter<AlphabetModel, AlphabetAdapter.MyViewHolder>(DIFF_CALLBACK) {

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
        val alphabet = getItem(position)
        if (alphabet != null) {
            holder.bind(alphabet)

             holder.itemView.findViewById<Button>(R.id.button).setOnClickListener{
                onItemClickCallback.onItemClicked(alphabet, holder.itemView.context)
            }
        }
    }

    interface OnItemClickCallback {
        fun onItemClicked(alphabet: AlphabetModel, context: Context)
    }

    class MyViewHolder(private val binding: AlphabetItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: AlphabetModel) {
            val btn = binding.button
            btn.text = item.alphabet.toString()
        }
    }

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<AlphabetModel>() {
            override fun areItemsTheSame(oldItem: AlphabetModel, newItem: AlphabetModel): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: AlphabetModel, newItem: AlphabetModel): Boolean {
                return oldItem.alphabet == newItem.alphabet
            }
        }
    }
}
