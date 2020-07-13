package com.reea.cnma.viewModels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.reea.cnma.models.Movie
import com.reea.cnma.repository.remote.MovieRepository

class MovieDetailsScreenViewModel(application: Application, movieTitle : String) : AndroidViewModel(application) {
    private var movieRepo = MovieRepository()
    private var movie : MutableLiveData<Movie> = movieRepo.getMovie(movieTitle)

    fun getMovieDetail() : MutableLiveData<Movie> {
        return movie
    }
}