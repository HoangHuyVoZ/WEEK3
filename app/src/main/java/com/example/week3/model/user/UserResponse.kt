package com.example.week3.model.user


import com.google.gson.annotations.SerializedName

data class UserResponse(
    @SerializedName("code")
    val code: Int,
    @SerializedName("data")
    val `data`: UserData,
    @SerializedName("message")
    val message: String,
    @SerializedName("status")
    var status: Boolean
)