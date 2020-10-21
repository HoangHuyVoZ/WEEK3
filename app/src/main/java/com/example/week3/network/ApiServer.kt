package com.example.week3.network

import com.example.week3.model.user.UserResponse
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST
import retrofit2.http.Query

interface ApiServer {

    @POST("users-login")
    @FormUrlEncoded
    fun getLogin(
        @Field("username") username: String,
        @Field("password") password: String,
        @Field("fcm_token") fcmToken: String
    ): Call<UserResponse>
}