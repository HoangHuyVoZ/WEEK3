package com.example.week3.ui.auth.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.example.week3.R
import kotlinx.android.synthetic.main.fragment_home__auth_.*
import kotlinx.android.synthetic.main.fragment_home__auth_.view.*
import kotlinx.android.synthetic.main.fragment_login__auth_.*


class Home_Auth_Fragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home__auth_, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        view.login.setOnClickListener {
            findNavController().navigate(R.id.action_home_Auth_Fragment_to_login_Auth_Fragment)
        }
        view.registerauth.setOnClickListener {
            findNavController().navigate(R.id.action_home_Auth_Fragment_to_create_Auth_Fragment)
        }
    }

    }
