package com.example.syngrychallenge.presentation.movieDetail

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.example.syngrychallenge.R
import com.example.syngrychallenge.data.remote.response.ApiResponse
import com.example.syngrychallenge.databinding.FragmentMovieDetailBinding
import com.example.syngrychallenge.domain.model.NewMoviesModel
import com.example.syngrychallenge.presentation.profile.CastsAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel

class MovieDetailFragment : Fragment() {
    private var _binding: FragmentMovieDetailBinding? = null
    private val binding get() = _binding!!
    private val viewModel: MovieDetailViewModel by viewModel()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMovieDetailBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val _movie = MovieDetailFragmentArgs.fromBundle(
            arguments as Bundle
        ).movie
        val _adapter = CastsAdapter()
        val movieId = _movie.id
        setUi(_movie)


        viewModel.movieCasts(movieId).observe(viewLifecycleOwner) { result ->
            when (result) {
                is ApiResponse.Success -> {
                    _adapter.submitList(result.data)
                    Log.d("pantek", _adapter.currentList.toString()  +"," + _adapter.itemCount)
                    binding.rvCasts.adapter = _adapter
                }

                is ApiResponse.Error -> {
                    Toast.makeText(activity, getString(R.string.response_failed), Toast.LENGTH_LONG)
                        .show()
                }

                is ApiResponse.Empty -> {
                    Toast.makeText(activity, getString(R.string.response_empty), Toast.LENGTH_LONG)
                        .show()
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setUi(movie: NewMoviesModel) {
        binding.movie = movie

        Glide
            .with(requireActivity())
            .load(getString(R.string.poster_url, movie.backdropPath))
            .into(binding.ivBackdrop)

    }
}