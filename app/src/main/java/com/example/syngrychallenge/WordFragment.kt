package com.example.syngrychallenge

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.syngrychallenge.adapter.WordAdapter
import com.example.syngrychallenge.databinding.FragmentAlphabetBinding
import com.example.syngrychallenge.model.WordListModel
import com.example.syngrychallenge.model.WordModel

class WordFragment : Fragment() {
    private lateinit var binding: FragmentAlphabetBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentAlphabetBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val _layoutManager = LinearLayoutManager(context)
        val _adapter = WordAdapter()
        val recyclerView = binding.rvAlphabets

        _adapter.submitList(generateWordList())
        recyclerView.apply {
            adapter = _adapter
            layoutManager = _layoutManager
        }

        _adapter.setOnItemClickCallback(object : WordAdapter.OnItemClickCallback {
            override fun onItemClicked(item: WordModel, context: Context) {
                val uri = Uri.parse(getString(R.string.google_url, item.word))
                val intent = Intent(Intent.ACTION_VIEW, uri)
                requireActivity().startActivity(intent)
            }
        })
    }

    private fun generateWordList(): List<WordModel> {
        val bundle = arguments
        val wordKey = bundle?.getChar(AlphabetFragment.ALPHABET)
        val wordListModel = WordListModel().wordAre
        val wordFiltered = wordListModel.filter {
            it.word.startsWith(wordKey!!, true)
        }
        return wordFiltered
    }

}