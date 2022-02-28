package com.example.mvvmwithretrofit.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.mvvmwithretrofit.api.MovieService
import com.example.mvvmwithretrofit.model.movie.MoviesList
import java.lang.Exception

class MovieRepository(private val movieService: MovieService) {

    private val moviesLiveData = MutableLiveData<Response<MoviesList>>()

    val movieList : LiveData<Response<MoviesList>>
    get() = moviesLiveData


    suspend fun getMoviesData(page : Int){
        try {
            val result = movieService.getMovies(page)
            if(result?.body() != null){
                moviesLiveData.postValue(Response.Success(result.body()))
            }else{
                moviesLiveData.postValue(Response.Error("API Error"))
            }
        }catch (e:Exception){
            moviesLiveData.postValue(Response.Error(e.message.toString()))

        }
    }


}