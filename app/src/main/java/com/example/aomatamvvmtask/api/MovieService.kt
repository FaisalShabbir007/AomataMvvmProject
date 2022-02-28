package com.example.mvvmwithretrofit.api

import com.example.mvvmwithretrofit.model.movie.MoviesList
import com.example.mvvmwithretrofit.util.Constants.Companion.API_KEY
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieService {


    @GET("/3/movie/popular?api_key=" + API_KEY)
    suspend fun getMovies(@Query("page") page: Int): Response<MoviesList>

}