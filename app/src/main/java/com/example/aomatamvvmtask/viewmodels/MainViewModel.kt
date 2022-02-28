package com.example.mvvmwithretrofit.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mvvmwithretrofit.model.movie.MoviesList
import com.example.mvvmwithretrofit.repository.MovieRepository
import com.example.mvvmwithretrofit.repository.Response
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel(private val repository: MovieRepository) : ViewModel() {

    init {
        viewModelScope.launch(Dispatchers.IO) {
            repository.getMoviesData(1)


        }
    }

    val movieList: LiveData<Response<MoviesList>>
        get() = repository.movieList

}