package com.reea.cnma.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import java.io.Serializable

@Entity(tableName = "movies_table")
data class Movie(
    @PrimaryKey(autoGenerate = true)
    var id : Int,

    @SerializedName("Title")
    var Title : String,

    @SerializedName("Year")
    var Year : String,

    @SerializedName("Rated")
    val Rated : String,

    @SerializedName("Genre")
    var Genre : String,

    @SerializedName("Plot")
    var Plot : String,

    @SerializedName("Language")
    val Language : String,

    @SerializedName("Poster")
    var Poster : String,

    @SerializedName("imdbRating")
    var ImdbRating : String,

    @SerializedName("Runtime")
    var Runtime : String,

    @SerializedName("Actors")
    var Actors : String,

    @SerializedName("Director")
    var Director : String) : Serializable {



}
