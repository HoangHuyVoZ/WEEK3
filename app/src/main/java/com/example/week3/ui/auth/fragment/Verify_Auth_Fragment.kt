package com.example.week3.ui.auth.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.week3.R
import com.example.week3.viewmodel.AuthViewModel
import kotlinx.android.synthetic.main.fragment_verify__auth_.*


class Verify_Auth_Fragment : Fragment() {
    lateinit var authViewModel: AuthViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        authViewModel=ViewModelProvider(this).get(AuthViewModel::class.java)
        return inflater.inflate(R.layout.fragment_verify__auth_, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        authViewModel.emailRegister?.observe(viewLifecycleOwner, Observer {
            name_mail1.text = it.toString()
        })
        backverify.setOnClickListener {
            findNavController().navigate(R.id.action_verify_Auth_Fragment_to_home_Auth_Fragment)
        }
        openmail.setOnClickListener {
            val emailIntent = Intent(Intent.ACTION_SEND)
            emailIntent.type = "text/plain"
            startActivity(emailIntent)
        }
    }

}