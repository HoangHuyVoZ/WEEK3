package com.example.week3.model.feed


import com.google.gson.annotations.SerializedName

data class Tag(
    @SerializedName("id")
    val id: Int,
    @SerializedName("title")
    val title: String
)