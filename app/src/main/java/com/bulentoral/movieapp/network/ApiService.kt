package com.bulentoral.movieapp.network

import com.bulentoral.movieapp.model.MovieResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header

interface ApiService {

    @GET("popular")
    suspend fun getMovieList(@Header("Authorization") token:String): Response<MovieResponse>
}