package com.example.syngrychallenge.presentation.moviesList

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.core.domain.model.MovieModel
import com.example.core.presentation.MoviesAdapter
import com.example.syngrychallenge.databinding.FragmentMoviesBinding
import com.example.syngrychallenge.utils.AppUtils.safeNavigate

class MoviesFragment : Fragment() {
    private val binding get() = _binding!!
    private var _binding: FragmentMoviesBinding? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMoviesBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        populateUI()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun populateUI() {
        val data = MoviesFragmentArgs.fromBundle(
            arguments as Bundle
        ).movies
        val moviesSectionLabel = MoviesFragmentArgs.fromBundle(
            arguments as Bundle
        ).label
        val _adapter = MoviesAdapter()

        _adapter.submitList(data.movies)
        binding.rvMovies.apply {
            adapter = _adapter
        }
        _adapter.onClick()

        binding.tvMoviesSectionLabel.text = moviesSectionLabel
    }

    private fun MoviesAdapter.onClick() {
        this.setOnClickCallback(object : MoviesAdapter.OnClickCallback {
            override fun onItemClicked(movie: MovieModel) {
                val destination =
                    MoviesFragmentDirections.actionMoviesFragmentToMovieDetailFragment(
                        movie
                    )
                findNavController().safeNavigate(destination)
            }

        })
    }
}