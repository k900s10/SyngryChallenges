package com.example.syngrychallenge.ui

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.example.syngrychallenge.R
import com.example.syngrychallenge.data.remote.response.ApiResponse
import com.example.syngrychallenge.databinding.FragmentHomeBinding
import com.example.syngrychallenge.domain.model.NewMoviesModel
import com.example.syngrychallenge.ui.adapter.HomeAdapter
import com.example.syngrychallenge.ui.viewModel.HomeViewModel
import com.example.syngrychallenge.utils.Util.safeNavigate
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : Fragment() {
    private val binding get() = _binding!!
    private var _binding: FragmentHomeBinding? = null
    private val viewModel: HomeViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val newMoviesAdapter = HomeAdapter()
        val popularMoviesAdapter = HomeAdapter()
        val rvNewMovie = binding.rvNewMovie
        val rvTrendingMovie = binding.rvTrendingMovie
        val btnProfile = binding.ivProfile
//        val username = HomeFragmentArgs.fromBundle(arguments as Bundle).username
//
//        binding.tvUsername.text = getString(R.string.tv_home_username, username)
        btnProfile.setOnClickListener {
            val destination = HomeFragmentDirections.actionHomeFragmentToProfileFragment()
            findNavController().navigate(destination)
        }

        viewModel.popularMovies.observe(viewLifecycleOwner) { result ->
            when (result) {
                is ApiResponse.Success -> {
                    val movies = result.data
                    bindPoster(movies[0])
                    popularMoviesAdapter.submitList(movies)
                }

                is ApiResponse.Error ->
                    Toast.makeText(activity, getString(R.string.response_failed), Toast.LENGTH_LONG)
                        .show()

                is ApiResponse.Empty ->
                    Toast.makeText(activity, getString(R.string.response_empty), Toast.LENGTH_LONG)
                        .show()
            }
        }

        viewModel.newMovies.observe(viewLifecycleOwner) { result ->
            when (result) {
                is ApiResponse.Success -> {
                    newMoviesAdapter.submitList(result.data)
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
        binding.rvNewMovie.adapter = newMoviesAdapter
        binding.rvTrendingMovie.adapter = popularMoviesAdapter

        popularMoviesAdapter.onClickPoster()
        newMoviesAdapter.onClickPoster()

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
//        requireArguments().remove("username")
    }

    private fun bindPoster(movie: NewMoviesModel) {
        Glide
            .with(requireActivity())
            .load(getString(R.string.poster_url, movie.posterPath))
            .into(binding.ivPromotionMoviePoster)

        binding.btnMovieShowcaseDetails.setOnClickListener {
            val destination =
                HomeFragmentDirections.actionHomeFragmentToMovieDetailFragment(movie)
            findNavController().safeNavigate(destination)
        }
    }

    private fun HomeAdapter.onClickPoster() {
        this.setOnClickCallback(
            object : HomeAdapter.OnClickCallback {
                override fun onItemClicked(movie: NewMoviesModel, context: Context) {
                    val destination =
                        HomeFragmentDirections.actionHomeFragmentToMovieDetailFragment(movie)
                    findNavController().safeNavigate(destination)
                }
            })

    }

    companion object {
        const val MOVIE_DETAIL = "movie detail"
    }
}