package com.example.week3.ui.auth.fragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.week3.R
import com.example.week3.ui.main.MainActivity
import kotlinx.android.synthetic.main.fragment_location.*
import kotlinx.android.synthetic.main.fragment_notification.*


class LocationFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_location, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        buttonLo.setOnClickListener {
            startActivity(Intent(context, MainActivity::class.java))
        }
        maybeLo.setOnClickListener {
            startActivity(Intent(context, MainActivity::class.java))
        }
    }


}