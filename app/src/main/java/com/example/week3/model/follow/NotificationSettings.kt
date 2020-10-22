package com.example.week3.model.follow


import com.google.gson.annotations.SerializedName

data class NotificationSettings(
    @SerializedName("app_update")
    val appUpdate: String,
    @SerializedName("comments")
    val comments: Any,
    @SerializedName("contact_joins_string")
    val contactJoinsString: String,
    @SerializedName("likes")
    val likes: Any,
    @SerializedName("near_you")
    val nearYou: String,
    @SerializedName("new_followes")
    val newFollowes: Any,
    @SerializedName("post_saves")
    val postSaves: Any,
    @SerializedName("processor")
    val processor: String,
    @SerializedName("string")
    val string: Any
)