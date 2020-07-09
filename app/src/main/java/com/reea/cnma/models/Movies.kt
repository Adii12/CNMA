package com.reea.cnma.models

import com.google.gson.annotations.SerializedName

data class Movies (
    @SerializedName("Search")
    var result : ArrayList<Movie>){}
