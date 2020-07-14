package com.reea.cnma.viewModels

import android.app.Activity
import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.reea.cnma.models.Movie
import com.reea.cnma.repository.local.DatabaseRepository

class FavoritesScreenViewModel(application: Application, activity: Activity) : AndroidViewModel(application) {
    private var databaseRepo : DatabaseRepository = DatabaseRepository(application, activity)
    private var favoriteMovies : LiveData<List<Movie>>? = databaseRepo.getAllMovies()

    fun getFavoriteMovies() : LiveData<List<Movie>>? {
        return favoriteMovies
    }
}