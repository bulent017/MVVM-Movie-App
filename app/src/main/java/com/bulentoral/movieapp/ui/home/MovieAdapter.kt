package com.bulentoral.movieapp.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bulentoral.movieapp.databinding.ItemViewBinding
import com.bulentoral.movieapp.model.MovieItem
import com.bulentoral.movieapp.util.loadCircleImage

class MovieAdapter(private val movieList: List<MovieItem?>, private val movieClickedListener: MovieClickListener) : RecyclerView.Adapter<MovieAdapter.ViewHolder>() {

    class ViewHolder(val binding : ItemViewBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return  ViewHolder(ItemViewBinding.inflate(LayoutInflater.from(parent.context), parent,false))
    }

    override fun getItemCount(): Int {
        return movieList.size
        }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val movie = movieList[position]
        holder.binding.textViewMovieTitle.text = movie?.title
        holder.binding.textViewOverview .text = movie?.overview
        holder.binding.textViewMovieVote.text = String.format("%.1f", movie?.voteAverage)
        holder.binding.imageViewMovie.loadCircleImage(movie?.posterPath)

        holder.binding.root.setOnClickListener {
            movieClickedListener.onMovieClicked(movieID = movie?.id)
        }


    }

}

interface MovieClickListener {
    fun onMovieClicked(movieID: Int?)
}