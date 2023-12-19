package com.bulentoral.movieapp.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.bulentoral.movieapp.R
import com.bulentoral.movieapp.databinding.FragmentHomeBinding


class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private lateinit var movieAdapter: MovieAdapter
    private val binding get() = _binding!!
    private val viewModel by viewModels<HomeViewModel>()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)


        observerEvents()

        return binding.root
    }

    private fun observerEvents() {
        viewModel.errorMessage.observe(viewLifecycleOwner){error ->
            binding.textViewHomeError.text = error
            binding.textViewHomeError.isVisible = true

        }
        viewModel.isLoading.observe(viewLifecycleOwner){loading ->
            binding.progressBarHome.isVisible = loading

        }
        viewModel.movieList.observe(viewLifecycleOwner){list ->
            if (list.isNullOrEmpty()){
                binding.textViewHomeError.text = "There is any movie"
                binding.textViewHomeError.isVisible = true
            }
            else{
                movieAdapter = MovieAdapter(list,object : MovieClickListener{
                    override fun onMovieClicked(movieID: Int?) {
                        movieID?.let {it
                            val action = HomeFragmentDirections.actionHomeFragmentToDetailFragment(it)
                            findNavController().navigate(action)
                        }

                    }
                })
                binding.reycyclerViewHome.adapter = movieAdapter
            }

                }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
