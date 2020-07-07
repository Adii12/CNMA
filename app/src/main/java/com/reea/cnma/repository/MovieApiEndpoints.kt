package com.reea.cnma.repository

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieApiEndpoints {

    @GET(".")
    fun getMovie(@Query("apikey") apikey : String, @Query("t") titleName : String) : Call<Movie>
}