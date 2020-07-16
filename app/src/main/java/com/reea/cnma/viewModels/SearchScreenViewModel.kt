package com.reea.cnma.viewModels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.reea.cnma.models.Movie
import com.reea.cnma.repository.remote.MovieRepository

class SearchScreenViewModel(application: Application) : AndroidViewModel(application) {
    private val movieRepo = MovieRepository()

    fun searchMovie(title : String) : MutableLiveData<List<Movie>>? {
        return movieRepo.searchMovie(title)
    }
}