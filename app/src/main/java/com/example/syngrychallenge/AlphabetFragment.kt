package com.example.syngrychallenge

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.syngrychallenge.adapter.AlphabetAdapter
import com.example.syngrychallenge.databinding.FragmentAlphabetBinding
import com.example.syngrychallenge.model.AlphabetModel

class AlphabetFragment : Fragment() {
    private lateinit var binding: FragmentAlphabetBinding
    private lateinit var _adapter: AlphabetAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAlphabetBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val _layoutManager = LinearLayoutManager(context)
        val _adapter = AlphabetAdapter()
        val recyclerView = binding.rvAlphabets

        _adapter.submitList(generateAlphabetList())
        recyclerView.apply {
            adapter = _adapter
            layoutManager = _layoutManager
        }

        _adapter.setOnItemClickCallback(object : AlphabetAdapter.OnItemClickCallback {
            override fun onItemClicked(alphabet: AlphabetModel, context: Context) {
                val toWordFragment =
                    AlphabetFragmentDirections.actionAlphabetFragmentToWordFragment()

                toWordFragment.alphabet = alphabet.alphabet.toString()
                findNavController().navigate(toWordFragment)
            }
        })
    }

    private fun generateAlphabetList(): List<AlphabetModel> {
        val alphabetList = mutableListOf<AlphabetModel>()
        for (i in 'A'..'Z') {
            val alphabet = AlphabetModel(i)
            alphabetList.add(alphabet)
        }
        return alphabetList
    }
}