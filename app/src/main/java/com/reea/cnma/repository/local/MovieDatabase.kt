package com.reea.cnma.repository.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.reea.cnma.models.Movie
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

@Database(entities = arrayOf(Movie::class), version = 1)
abstract class MovieDatabase : RoomDatabase() {
    abstract fun movieDAO() : MovieDAO

    companion object {
        private const val NUMBER_OF_THREADS = 4
        val databaseWriteExecutor : ExecutorService = Executors.newFixedThreadPool(NUMBER_OF_THREADS)

        @Volatile
        private var instance : MovieDatabase? = null

        fun getInstance(context : Context) : MovieDatabase? {
            if(instance == null) {
                synchronized(MovieDatabase::class) {
                    instance = Room.databaseBuilder(context.applicationContext, MovieDatabase::class.java, "movies_database")
                        .fallbackToDestructiveMigration()
                        .build()
                }
            }
            return instance
        }
    }

    fun destroyDatabase() {
        instance = null
    }
}