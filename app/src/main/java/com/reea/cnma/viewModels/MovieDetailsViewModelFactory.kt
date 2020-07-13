package com.reea.cnma.viewModels

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class MovieDetailsViewModelFactory(private val movieTitle : String, var application: Application) : ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return when(modelClass) {
            MovieDetailsScreenViewModel::class.java -> MovieDetailsScreenViewModel(application, movieTitle)

            else -> throw IllegalStateException("Unknown ViewModel Class")
        } as T
    }
}