package com.reea.cnma.repository

import com.google.gson.annotations.SerializedName

data class Movie(
    var Title : String,
    var Year : String,
    val Rated : String,
    var Genre : String,
    var Plot : String,
    val Language : String,
    var Poster : String,
    @SerializedName("imdbRating")
    var ImdbRating : String,
    var Runtime : String,
    var Actors : String) {



}