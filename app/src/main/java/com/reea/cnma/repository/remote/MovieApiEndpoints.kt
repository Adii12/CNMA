package com.reea.cnma.repository.remote

import com.reea.cnma.models.Movie
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieApiEndpoints {

    @GET(".")
    fun getMovie(@Query("apikey") apikey : String, @Query("t") titleName : String) : Call<Movie>

    @GET(".")
    fun searchMovie(@Query("apikey") apikey : String, @Query("s") searchText : String) : Call<List<Movie>>
}