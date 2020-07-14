package com.reea.cnma.viewModels

import android.app.Activity
import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class ViewModelFactory(private val movieTitle : String, var application: Application, var activity : Activity) : ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return when(modelClass) {
            MovieDetailsScreenViewModel::class.java -> MovieDetailsScreenViewModel(application, movieTitle)

            FavoritesScreenViewModel::class.java -> FavoritesScreenViewModel(application, activity)

            else -> throw IllegalStateException("Unknown ViewModel Class")
        } as T
    }
}