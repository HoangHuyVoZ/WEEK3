package com.example.week3.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.week3.model.follow.Follow
import com.example.week3.model.follow.FollowData
import com.example.week3.model.interest.Interest
import com.example.week3.model.interest.InterestData
import com.example.week3.model.user.UserResponse
import com.example.week3.network.API
import com.example.week3.network.ApiServer
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit

class AuthViewModel : ViewModel() {
    var dataLogin: MutableLiveData<UserResponse>? = MutableLiveData<UserResponse>()
    var dataRegister: MutableLiveData<UserResponse>? = MutableLiveData<UserResponse>()
    var dataForgot: MutableLiveData<UserResponse>? = MutableLiveData<UserResponse>()
    var emailRegister: MutableLiveData<String>? = MutableLiveData<String>()
    var dataInterest: MutableLiveData<Interest>? = MutableLiveData<Interest>()
    var dataInterested: MutableLiveData<List<InterestData>>? = MutableLiveData<List<InterestData>>()
    var dataPostInterest: MutableLiveData<Interest>? = MutableLiveData<Interest>()
    var dataFollow: MutableLiveData<FollowData>? = MutableLiveData<FollowData>()
    var dataFollow1: MutableLiveData<List<Follow>>? = MutableLiveData<List<Follow>>()
    var dataPostFollow: MutableLiveData<UserResponse>? = MutableLiveData<UserResponse>()
    var ListCount: MutableLiveData<Int>? = MutableLiveData<Int>()


    fun getPostFollow(accesstoken: String,user_id:Int){
        val request = API.buildService(ApiServer::class.java)
        val call = request.getPostFollow(accesstoken, user_id)

        call.enqueue(object : Callback<UserResponse> {
            override fun onFailure(call: Call<UserResponse>, t: Throwable) {
                dataPostFollow?.postValue(null)
            }

            override fun onResponse(call: Call<UserResponse>, response: Response<UserResponse>) {
                if (response.isSuccessful) {
                    dataPostFollow?.postValue(response.body())
                } else {
                    dataPostFollow?.postValue(null)


                }
            }
        })
    }
    fun getFollow(page: Int, accesstoken: String, current_page: Int) {
        val request = API.buildService(ApiServer::class.java)
        val call = request.getFollowlist(accesstoken, page, current_page)

        call.enqueue(object : Callback<FollowData> {
            override fun onFailure(call: Call<FollowData>, t: Throwable) {
                dataFollow?.postValue(null)
                dataFollow1?.postValue(null)
            }

            override fun onResponse(call: Call<FollowData>, response: Response<FollowData>) {
                if (response.isSuccessful) {
                    dataFollow?.postValue(response.body())
                    dataFollow1?.postValue(response.body()?.data)
                } else {
                    dataFollow?.postValue(null)
                    dataFollow1?.postValue(null)

                }
            }
        })
    }

    fun getPostInterest(accesstoken: String, listsInterest: ArrayList<Int>) {
        Log.d("huy", listsInterest.toString())
        val request = API.buildService(ApiServer::class.java)
        val call = request.getPostInterest(accesstoken, listsInterest)

        call.enqueue(object : Callback<Interest> {
            override fun onFailure(call: Call<Interest>, t: Throwable) {
                dataPostInterest?.postValue(null)
            }

            override fun onResponse(call: Call<Interest>, response: Response<Interest>) {
                if (response.isSuccessful) {
                    dataPostInterest?.postValue(response.body())
                } else {
                    dataPostInterest?.postValue(null)
                }
            }
        })
    }

    fun getInterest(accesstoken: String) {
        val request = API.buildService(ApiServer::class.java)
        val call = request.getListInterest(accesstoken)

        call.enqueue(object : Callback<Interest> {
            override fun onFailure(call: Call<Interest>, t: Throwable) {
                dataInterest?.postValue(null)
                dataInterested?.postValue(null)

            }

            override fun onResponse(call: Call<Interest>, response: Response<Interest>) {
                if (response.isSuccessful) {
                    dataInterested?.postValue(response.body()?.data)
                    dataInterest?.postValue(response.body())

                } else {
                    dataInterest?.postValue(null)
                    dataInterested?.postValue(null)

                }
            }
        })
    }

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

    fun getForgotPassword(email: String) {
        val request = API.buildService(ApiServer::class.java)
        val call = request.getForgot(email)

        call.enqueue(object : Callback<UserResponse> {
            override fun onFailure(call: Call<UserResponse>, t: Throwable) {
                dataForgot?.postValue(null)

            }

            override fun onResponse(call: Call<UserResponse>, response: Response<UserResponse>) {
                if (response.isSuccessful) {
                    dataForgot?.postValue(response.body())

                } else {
                    dataForgot?.postValue(null)

                }
            }
        })
    }

    fun getRegister(
        username: String,
        name: String,
        dayOfBirth: String,
        email: String,
        password: String,
        confirmPassword: String
    ) {
        val request = API.buildService(ApiServer::class.java)
        val call = request.getRegister(username, name, dayOfBirth, email, password, confirmPassword)

        call.enqueue(object : Callback<UserResponse> {
            override fun onFailure(call: Call<UserResponse>, t: Throwable) {
                dataRegister?.postValue(null)

            }

            override fun onResponse(call: Call<UserResponse>, response: Response<UserResponse>) {
                if (response.isSuccessful) {
                    dataRegister?.postValue(response.body())

                } else {
                    dataRegister?.postValue(null)

                }
            }
        })
    }
}


