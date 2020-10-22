package com.example.week3.ui.auth.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.week3.R
import com.example.week3.model.interest.InterestData
import com.example.week3.ui.auth.apdapter.InterestAdapter
import com.example.week3.utils.Constants
import com.example.week3.utils.Constants.Companion.ACCESS_TOKEN
import com.example.week3.utils.Constants.Companion.AUTHORIZATION
import com.example.week3.utils.OnClickItem
import com.example.week3.utils.View.Companion.invisible
import com.example.week3.utils.View.Companion.visible
import com.example.week3.viewmodel.AuthViewModel
import kotlinx.android.synthetic.main.fragment_interest.*
import kotlinx.android.synthetic.main.fragment_login__auth_.*


class InterestFragment() : Fragment(),OnClickItem {
    lateinit var authViewModel: AuthViewModel
    lateinit var layoutManager: GridLayoutManager
    lateinit var adapter: InterestAdapter
    private var count = 3




    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        authViewModel = ViewModelProvider(this).get(AuthViewModel::class.java)
        return inflater.inflate(R.layout.fragment_interest, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        init()
        notifyerr_inte.invisible()
        LoginAgian_Interest.invisible()
        dataInterest()
        dataPostInterest()
        Log.d("d",adapter.addlistInterest().toString())
        authViewModel.ListCount?.observe(viewLifecycleOwner, Observer {
            if(it>=3){
                btn_next_in.isEnabled =true
                btn_next_in.text = " Next"
                btn_next_in.backgroundTintList= ContextCompat.getColorStateList(requireContext(), R.color.colorPurple)

            }else{
                btn_next_in.isEnabled=false
                btn_next_in.backgroundTintList= ContextCompat.getColorStateList(requireContext(), R.color.colorGrayEnable)
                btn_next_in.text = getString(R.string.more,(3-it))
            }
        })
    }

    private fun dataPostInterest() {
        authViewModel.dataPostInterest?.observe(viewLifecycleOwner, Observer {
            if(it!=null){
                if(!it.status){
                    notifyerr_inte.text=it.message
                    notifyerr_inte.visible()
                    LoginAgian_Interest.visible()
                }else{
                    findNavController().navigate(R.id.action_interestFragment_to_followFragment)
                }
            }
        })
    }


    private fun dataInterest() {
        authViewModel.dataInterest?.observe(viewLifecycleOwner, Observer {
            if(it!=null){
                if(!it.status){
                    notifyerr_inte.text=it.message
                    LoginAgian_Interest.visible()
                    notifyerr_inte.visible()

                }else{
                    authViewModel.dataInterested?.observe(viewLifecycleOwner, Observer {
                        if(it!=null){
                            adapter.addList(it as MutableList<InterestData>)
                            adapter.notifyDataSetChanged()
                            count = adapter.count()
                            if(count>=3){
                                btn_next_in.isEnabled=true
                                btn_next_in.backgroundTintList= ContextCompat.getColorStateList(requireContext(), R.color.colorPurple)
                                btn_next_in.text = " Next"
                            }else{
                                btn_next_in.isEnabled=false
                                btn_next_in.backgroundTintList= ContextCompat.getColorStateList(requireContext(), R.color.colorGrayEnable)
                                btn_next_in.text = getString(R.string.more,(3-count))
                            }
                        }
                    })
                }
            }
        })

    }

    private fun init() {
        layoutManager = GridLayoutManager(requireContext(), 2)
        recyclerViewInterest.setHasFixedSize(true)
        recyclerViewInterest.layoutManager = layoutManager
        adapter = InterestAdapter(this)
        recyclerViewInterest.adapter = adapter
        val pref = requireContext().getSharedPreferences(Constants.PREFS_NAME, AppCompatActivity.MODE_PRIVATE)
        val accessToken= pref.getString(ACCESS_TOKEN,"")
        if (accessToken != null) {
            authViewModel.getInterest(AUTHORIZATION+accessToken)
        }
        btn_next_in.setOnClickListener {
                var list = adapter.addlistInterest()
                Log.d("data",list.toString())
                if (list != null) {
                    authViewModel.getPostInterest(AUTHORIZATION+accessToken,list)
                    findNavController().navigate(R.id.action_interestFragment_to_followFragment)
                }
            }
        }

    override fun OnClickItem(listCount: Int) {
            authViewModel.ListCount?.value= listCount

    }

}


