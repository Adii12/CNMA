package com.reea.cnma.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "movies_table")
data class Movie(
    @PrimaryKey(autoGenerate = true)
    var id : Int,
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