package com.reea.cnma.repository

import androidx.lifecycle.MutableLiveData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MovieRepository() {
    private val baseURL = "https://www.omdbapi.com/"
    private val apikey = "90ac03f8"

    private var retrofit : Retrofit = Retrofit.Builder()
        .baseUrl(baseURL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private var movieService: MovieApiEndpoints = retrofit.create(MovieApiEndpoints::class.java)

    fun getMovie(title : String) : MutableLiveData<Movie>?{
        var result  = MutableLiveData<Movie>()

        val call = movieService.getMovie(apikey, title)

        call.enqueue(object : Callback<Movie> {
            override fun onFailure(call: Call<Movie>, t: Throwable) {
              t.printStackTrace()
            }

            override fun onResponse(call: Call<Movie>, response: Response<Movie>) {
                if(response.isSuccessful){
                    result.value = response.body()
                }
            }
        })

        return result
    }

}