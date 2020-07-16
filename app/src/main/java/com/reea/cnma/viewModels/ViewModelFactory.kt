package com.reea.cnma.viewModels

import android.app.Activity
import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class ViewModelFactory(private val text : String, var application: Application, private var activity : Activity) : ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return when(modelClass) {
            MovieDetailsScreenViewModel::class.java -> MovieDetailsScreenViewModel(application, text)

            FavoritesScreenViewModel::class.java -> FavoritesScreenViewModel(application, activity)
            else -> throw IllegalStateException("Unknown ViewModel Class")
        } as T
    }
}