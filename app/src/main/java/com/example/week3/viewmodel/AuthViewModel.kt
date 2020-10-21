package com.example.week3.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.week3.model.user.UserResponse
import com.example.week3.network.API
import com.example.week3.network.ApiServer
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit

class AuthViewModel : ViewModel() {
      var dataLogin : MutableLiveData<UserResponse>? = MutableLiveData<UserResponse>()


    fun getLogin(username: String, password: String, fcmToken: String) {
        val request = API.buildService(ApiServer::class.java)
        val call = request.getLogin(username, password, fcmToken)

        call.enqueue(object : Callback<UserResponse> {
            override fun onFailure(call: Call<UserResponse>, t: Throwable) {
                dataLogin?.postValue(null)

            }

            override fun onResponse(call: Call<UserResponse>, response: Response<UserResponse>) {
                if (response.isSuccessful) {
                    dataLogin?.postValue(response.body())

                } else {
                    dataLogin?.postValue(null)

                }
            }
        })
    }
}