package com.example.week3.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.week3.model.feed.Feed
import com.example.week3.model.feed.FeedData
import com.example.week3.model.user.UserResponse
import com.example.week3.network.API
import com.example.week3.network.ApiServer
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FeedViewModel :ViewModel() {
    var dataFeed: MutableLiveData<Feed> = MutableLiveData<Feed>()
    var dataFeedList: MutableLiveData<List<FeedData>> = MutableLiveData<List<FeedData>>()
    var errorData = MutableLiveData<Boolean>().apply { value = false }


    fun getFeed(page: Int, accesstoken: String, current_page: Int){
        val request = API.buildService(ApiServer::class.java)
        val call = request.getFeed(accesstoken,page,current_page)

        call.enqueue(object : Callback<Feed> {
            override fun onFailure(call: Call<Feed>, t: Throwable) {
                dataFeed?.postValue(null)
                dataFeedList?.postValue(null)
            }

            override fun onResponse(call: Call<Feed>, response: Response<Feed>) {
                if (response.isSuccessful) {
                        dataFeed?.postValue(response.body())
                        dataFeedList?.postValue(response.body()?.data)
                    errorData.value = false

                } else {
                    dataFeed?.postValue(null)
                    dataFeedList?.postValue(null)

                }
            }
        })
    }
}