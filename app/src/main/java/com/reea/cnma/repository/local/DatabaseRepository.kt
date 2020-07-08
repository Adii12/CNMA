package com.reea.cnma.repository.local

import android.app.Application
import androidx.lifecycle.LiveData
import com.reea.cnma.models.Movie

class DatabaseRepository(application: Application) {
    private var movieDAO : MovieDAO?
    private var allMovies : LiveData<List<Movie>>?
    private val database : MovieDatabase? = MovieDatabase.getInstance(application)

    init {
        movieDAO = database?.movieDAO()
        allMovies = movieDAO?.selectAllMovies()
    }

    fun insert(movie: Movie) {
        MovieDatabase.databaseWriteExecutor.execute { movieDAO?.insert(movie) }
    }

    fun delete(movie: Movie) {
        MovieDatabase.databaseWriteExecutor.execute { movieDAO?.delete(movie) }
    }

    fun deleteAllMovies() {
        MovieDatabase.databaseWriteExecutor.execute {movieDAO?.deleteAllMovies()}
    }

    fun getAllMovies() : LiveData<List<Movie>>? {
        return allMovies
    }
}