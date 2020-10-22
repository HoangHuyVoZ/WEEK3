package com.example.week3.ui.auth.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.week3.R
import com.example.week3.model.follow.Follow
import com.example.week3.model.interest.InterestData
import com.example.week3.ui.auth.apdapter.FollowAdapter
import com.example.week3.ui.auth.apdapter.InterestAdapter
import com.example.week3.utils.Constants
import com.example.week3.utils.Constants.Companion.AUTHORIZATION
import com.example.week3.utils.OnClickItem
import com.example.week3.viewmodel.AuthViewModel
import kotlinx.android.synthetic.main.fragment_follow.*
import kotlinx.android.synthetic.main.fragment_interest.*


class FollowFragment : Fragment(),OnClickItem {

    lateinit var authViewModel: AuthViewModel
    lateinit var layoutManager:LinearLayoutManager
    lateinit var adapter: FollowAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        authViewModel = ViewModelProvider(this).get(AuthViewModel::class.java)
        return inflater.inflate(R.layout.fragment_follow, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        init()
        dataFollow()
        backFollow.setOnClickListener {
            findNavController().popBackStack()
        }
        btn_next_Follow.setOnClickListener {
            findNavController().navigate(R.id.action_followFragment_to_notificationFragment)
        }
    }

    private fun dataFollow() {
        authViewModel.dataFollow1?.observe(viewLifecycleOwner, Observer {
            if(it!=null){
                adapter.addList(it as MutableList<Follow>)
                adapter.notifyDataSetChanged()
            }
        })

    }

    private fun init() {
        layoutManager = LinearLayoutManager(context)
        recyclerViewFollow.setHasFixedSize(true)
        recyclerViewFollow.layoutManager = layoutManager
        adapter = FollowAdapter(this)
        recyclerViewFollow.adapter = adapter
        val pref = requireContext().getSharedPreferences(Constants.PREFS_NAME, AppCompatActivity.MODE_PRIVATE)
        val accessToken= pref.getString(Constants.ACCESS_TOKEN,"")
        if (accessToken != null) {
            authViewModel.getFollow(0,AUTHORIZATION +accessToken,0)
        }
    }

    override fun OnClickItem(list: Int) {
        val pref = requireContext().getSharedPreferences(Constants.PREFS_NAME, AppCompatActivity.MODE_PRIVATE)
        val accessToken= pref.getString(Constants.ACCESS_TOKEN,"")
            if(list!=null){
                authViewModel.getPostFollow(AUTHORIZATION+accessToken,list)
            }
    }

}