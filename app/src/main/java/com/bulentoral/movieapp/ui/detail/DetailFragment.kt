package com.bulentoral.movieapp.ui.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bulentoral.movieapp.MainActivity
import com.bulentoral.movieapp.R
import com.bulentoral.movieapp.databinding.FragmentDetailBinding
import com.bulentoral.movieapp.databinding.FragmentHomeBinding
import com.bulentoral.movieapp.ui.home.HomeFragmentDirections
import com.bulentoral.movieapp.ui.home.MovieAdapter
import com.bulentoral.movieapp.ui.home.MovieClickListener
import com.bulentoral.movieapp.util.loadImage


class DetailFragment : Fragment() {
    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding!!
    private val viewModel by viewModels<DetailViewModel>()
    private val args by navArgs<DetailFragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDetailBinding.inflate(inflater, container, false)

        viewModel.getMovieDetail(movieID = args.movieID)
        observerEvents()
        return binding.root
    }


    private fun observerEvents() {
        viewModel.errorMessage.observe(viewLifecycleOwner){error ->
            binding.textViewErrorDetail.text = error
            binding.textViewErrorDetail.isVisible = true

        }
        viewModel.isLoading.observe(viewLifecycleOwner){loading ->
            binding.progressBarDetail.isVisible = loading

        }
        viewModel.movieDetail.observe(viewLifecycleOwner){movie ->

            binding.apply {
                textViewDetailLanguage.text = movie.spokenLanguages?.first()?.englishName
                textViewDetailOverview.text = movie.overview
                textViewDetailVote.text = movie.voteAverage.toString()
                textViewDetailStudio.text = movie.productionCompanies?.first()?.name
                groupDetail.isVisible = true

                imageViewDetail.loadImage(movie.backdropPath)
                (requireActivity() as MainActivity).supportActionBar?.title=movie.title

            }



        }

    }



    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}