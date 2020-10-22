package com.example.week3.model.follow


import com.google.gson.annotations.SerializedName

data class FollowData(
    @SerializedName("code")
    val code: Int,
    @SerializedName("data")
    val `data`: ArrayList<Follow>,
    @SerializedName("message")
    val message: String,
    @SerializedName("metadata")
    val metadata: Metadata,
    @SerializedName("status")
    val status: Boolean
)