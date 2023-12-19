package com.bulentoral.movieapp.network

import com.bulentoral.movieapp.model.MovieDetalResponse
import com.bulentoral.movieapp.model.MovieResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path

interface ApiService {

    @GET("popular")
    suspend fun getMovieList(@Header("Authorization") token:String): Response<MovieResponse>

    @GET("{movieID}")
    suspend fun  getMovieDetail(@Path("movieID") movieID:String, @Header("Authorization") token: String): Response<MovieDetalResponse>
}