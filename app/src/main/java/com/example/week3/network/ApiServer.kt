package com.example.week3.network

import com.example.week3.model.follow.FollowData
import com.example.week3.model.interest.Interest
import com.example.week3.model.user.UserResponse
import retrofit2.Call
import retrofit2.http.*

interface ApiServer {

    @POST("users-login")
    @FormUrlEncoded
    fun getLogin(
        @Field("username") username: String,
        @Field("password") password: String,
        @Field("fcm_token") fcmToken: String
    ): Call<UserResponse>
    @FormUrlEncoded
    @POST("users-register-email")
    fun getRegister(
        @Field("username") username: String,
        @Field("name") name: String,
        @Field("date_of_birth") dayOfBirth: String,
        @Field("email") email: String,
        @Field("password") password: String,
        @Field("confirm_password") confirmPassword: String
    ): Call<UserResponse>
    @FormUrlEncoded
    @POST("users-forget-password")
    fun getForgot(
        @Field("email") email: String
    ): Call<UserResponse>

    @GET("interest-categories-list")
    fun getListInterest(
        @Header("Authorization") authorization: String
    ): Call<Interest>

    @PUT("users-interest-categories-select")
    fun getPostInterest(
        @Header("Authorization") authorization: String,
        @Query("lists_interest[]") listsInterest: ArrayList<Int>
    ): Call<Interest>

    @GET("users-list")
    fun getFollowlist(
        @Header("Authorization") authentication: String,
        @Query("page") page: Int,
        @Query("current_per_page") currentPerPage: Int
    ): Call<FollowData>

    @POST("follow-users")
    fun getPostFollow(
        @Header("Authorization") authorization: String,
        @Query("users_id") userID: Int
    ): Call<UserResponse>
}