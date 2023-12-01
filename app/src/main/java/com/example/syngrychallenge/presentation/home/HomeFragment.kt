package com.example.syngrychallenge.presentation.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.core.domain.model.MovieModel
import com.example.core.domain.model.MoviesModel
import com.example.core.presentation.MoviesAdapter
import com.example.core.utils.CoreConstant
import com.example.core.utils.result.GetMoviesResult
import com.example.syngrychallenge.R
import com.example.syngrychallenge.databinding.FragmentHomeBinding
import com.example.syngrychallenge.utils.AppUtils.safeNavigate
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

        val btnProfile = binding.ivProfile

        btnProfile.setOnClickListener {
            val destination =
                HomeFragmentDirections.actionHomeFragmentToProfileFragment()
            findNavController().navigate(destination)
        }

        viewModel.popularMovies.observe(viewLifecycleOwner) { result ->
            when (result) {
                is GetMoviesResult.Success -> {
                    val movies = result.data
                    val maxMovies = movies.maxMovies()

                    bindPoster(movies[0])
//                    popularMoviesAdapter.submitList(maxMovies)

                    val recyclerview = binding.includeTrendingMovies.rvMoviesList
                    val label = binding.includeTrendingMovies.tvMoviesLabel

                    recyclerview.populateMoviesList(maxMovies)
                    //naming
                    label.text = getString(R.string.tv_home_popular_label)
                    label.setOnSeeAllClickListener(movies)

                    binding.includeNewMovies.root.visibility = View.VISIBLE

                }

                is GetMoviesResult.Failed -> {
                    Toast.makeText(activity, getString(R.string.response_failed), Toast.LENGTH_LONG)
                        .show()
                    binding.includeNewMovies.root.visibility = View.GONE
                }
            }
        }

        viewModel.newMovies.observe(viewLifecycleOwner) { result ->
            when (result) {
                is GetMoviesResult.Success -> {
                    val movies = result.data
                    val recyclerview = binding.includeNewMovies.rvMoviesList
                    val label = binding.includeNewMovies.tvMoviesLabel

                    recyclerview.populateMoviesList(movies.maxMovies())
                    label.text = getString(R.string.tv_home_new_movie_label)
                    label.setOnSeeAllClickListener(movies)
                    binding.includeNewMovies.root.visibility = View.VISIBLE
//                    newMoviesAdapter.submitList(maxMovies)
                }

                is GetMoviesResult.Failed -> {
                    Toast.makeText(activity, getString(R.string.response_failed), Toast.LENGTH_LONG)
                        .show()
                    binding.includeNewMovies.root.visibility = View.GONE
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
//        requireArguments().remove("username")
    }

    private fun RecyclerView.populateMoviesList(
        movies: List<MovieModel>
    ) {
        val _adapter = MoviesAdapter()
        val maxMovies = movies.maxMovies()

        //adding adapter & set the click of each poster
        _adapter.submitList(maxMovies)
        this.adapter = _adapter
        _adapter.onClickPoster()
    }

    private fun TextView.setOnSeeAllClickListener(movies: List<MovieModel>) {
        //set see all navigation
        this.setOnClickListener {
            val destination =
                HomeFragmentDirections.actionHomeFragmentToMoviesFragment(
                    MoviesModel(movies),
                    binding.includeTrendingMovies.tvMoviesLabel.text.toString()
                )

            findNavController().safeNavigate(destination)
        }

    }

    //set the max of showable movie in recyclerview gridLayout
    private fun List<MovieModel>.maxMovies(): List<MovieModel> =
        if (this.size > 8)
            this.take(8)
        else
            this


    private fun bindPoster(movie: MovieModel) {
        Glide
            .with(requireActivity())
            .load(CoreConstant.MOVIE_POSTER_URL + movie.posterPath)
            .into(binding.ivPromotionMoviePoster)

        binding.btnMovieShowcaseDetails.setOnClickListener {
            val destination =
                HomeFragmentDirections.actionHomeFragmentToMovieDetailFragment(
                    movie
                )

            findNavController().safeNavigate(destination)
        }
    }

    private fun MoviesAdapter.onClickPoster() {
        this.setOnClickCallback(
            object : MoviesAdapter.OnClickCallback {
                override fun onItemClicked(movie: MovieModel) {
                    val destination =
                        HomeFragmentDirections.actionHomeFragmentToMovieDetailFragment(
                            movie
                        )
                    findNavController().safeNavigate(destination)
                }
            })
    }
}