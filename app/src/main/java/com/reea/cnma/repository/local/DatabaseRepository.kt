package com.reea.cnma.repository.local

import android.app.Activity
import android.app.Application
import android.os.AsyncTask
import androidx.lifecycle.LiveData
import com.reea.cnma.models.Movie

class DatabaseRepository(application: Application, activity: Activity) {
    private var movieDAO : MovieDAO?
    private var allMovies : LiveData<List<Movie>>?
    private val database : MovieDatabase? = MovieDatabase.getInstance(application)
    private var movie : Movie? = null
    private val mActivity = activity

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

    fun deleteMovie(title: String) {
        MovieDatabase.databaseWriteExecutor.execute {movieDAO?.deleteMovie(title)}
    }

    fun deleteAllMovies() {
        MovieDatabase.databaseWriteExecutor.execute {movieDAO?.deleteAllMovies()}
    }

    fun getMovie(title: String) : Movie? {
       class async :  AsyncTask<String, Void, Movie>(){
           override fun doInBackground(vararg title: String): Movie? {
               return movieDAO?.getMovie(title[0])
           }

           override fun onPostExecute(result: Movie?) {
               super.onPostExecute(result)
               movie = result
           }
       }

        val Async = async()
        movie = Async.execute(title).get()
        return movie

    }

    fun getAllMovies() : LiveData<List<Movie>>? {
        return allMovies
    }
}