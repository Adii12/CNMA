package com.reea.cnma.repository.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.reea.cnma.models.Movie

@Dao
interface MovieDAO {
    @Insert
    fun insert(movie : Movie)

    @Delete
    fun delete(movie : Movie)

    @Query("SELECT * FROM movies_table")
    fun selectAllMovies() : LiveData<List<Movie>>

    @Query("DELETE FROM movies_table")
    fun deleteAllMovies()
}