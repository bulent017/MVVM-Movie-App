package com.bulentoral.movieapp.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import com.bulentoral.movieapp.R
import com.bulentoral.movieapp.databinding.FragmentHomeBinding


class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private val viewModel by viewModels<HomeViewModel>()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)


        viewModel.getMovieList()
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
            print(list)

        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
