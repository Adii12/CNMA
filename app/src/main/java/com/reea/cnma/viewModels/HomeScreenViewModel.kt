package com.reea.cnma.viewModels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.reea.cnma.models.Movie
import com.reea.cnma.repository.remote.MovieRepository

class HomeScreenViewModel(application: Application) : AndroidViewModel(application) {
    private var movieRepo : MovieRepository = MovieRepository()
    private var allMovies : MutableLiveData<List<Movie>>? = movieRepo.searchMovie("movie")

    fun getMovies() : MutableLiveData<List<Movie>>? {
        return allMovies
    }
}