package com.example.syngrychallenge.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageButton
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.syngrychallenge.R
import com.example.syngrychallenge.databinding.ItemNoteBinding
import com.example.syngrychallenge.domain.model.NoteModel

class HomeAdapter :
    ListAdapter<NoteModel, HomeAdapter.MyViewHolder>(DIFF_CALLBACK) {

    private lateinit var onUpdatelickCallback: OnUpdatelickCallback
    private lateinit var onDeleteClickCallback: OnDeletelickCallback

    fun setOnUpdateClickCallback(onUpdatelickCallback: OnUpdatelickCallback) {
        this.onUpdatelickCallback = onUpdatelickCallback
    }

    fun setOnDeleteClickCallback(onDeletelickCallback: OnDeletelickCallback) {
        this.onDeleteClickCallback = onDeletelickCallback
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding =
            ItemNoteBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val note = getItem(position)
        if (note != null) {
            holder.bind(note)
            holder.itemView.findViewById<ImageButton>(R.id.ib_edit).setOnClickListener {
                onUpdatelickCallback.onItemClicked(note, holder.itemView.context)
            }
            holder.itemView.findViewById<ImageButton>(R.id.ib_delete).setOnClickListener {
                onDeleteClickCallback.onItemClicked(note, holder.itemView.context)
            }
        }
    }

    interface OnUpdatelickCallback {
        fun onItemClicked(note: NoteModel, context: Context)
    }

    interface OnDeletelickCallback {
        fun onItemClicked(note: NoteModel, context: Context)
    }


    class MyViewHolder(private val binding: ItemNoteBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(note: NoteModel) {
            binding.notes = note
        }
    }

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<NoteModel>() {
            override fun areItemsTheSame(oldItem: NoteModel, newItem: NoteModel): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: NoteModel, newItem: NoteModel): Boolean {
                return oldItem.id == newItem.id
            }
        }
    }
}