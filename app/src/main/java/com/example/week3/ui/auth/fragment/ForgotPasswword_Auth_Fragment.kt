package com.example.week3.ui.auth.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.week3.R
import com.example.week3.utils.View.Companion.getTextTrim
import com.example.week3.utils.View.Companion.hideKeyboard
import com.example.week3.utils.View.Companion.invisible
import com.example.week3.utils.View.Companion.isValidEmail
import com.example.week3.utils.View.Companion.visible
import com.example.week3.viewmodel.AuthViewModel
import kotlinx.android.synthetic.main.fragment_forgot_passwword__auth_.*
import kotlinx.android.synthetic.main.fragment_login__auth_.*


class ForgotPasswword_Auth_Fragment : Fragment() {
    lateinit var authViewModel: AuthViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        authViewModel = ViewModelProvider(this).get(AuthViewModel::class.java)
        return inflater.inflate(R.layout.fragment_forgot_passwword__auth_, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        progressBarFor.invisible()
        notifyerrFor.invisible()
        init()
        getDataForgot()
    }

    private fun getDataForgot() {
        authViewModel.dataForgot?.observe(viewLifecycleOwner, Observer {
            if(it!=null){
                if(it.status){
                    progressBarFor.invisible()
                    notifyerrFor.visible()
                    notifyerrFor.text= it.message
                }else{
                    progressBarFor.invisible()
                    notifyerrFor.visible()
                    notifyerrFor.text= it.message
                }
            }
        })
    }

    private fun init() {
        backForgot.setOnClickListener {
            findNavController().popBackStack()
        }
        btnSendlink.setOnClickListener {
            it.hideKeyboard()
            checkForgot()
        }
    }

    private fun checkForgot() {
        if (!isValidEmail(edtEmail_for.getTextTrim()))
            edtEmail.error = "Enter a valid email"
        else if(isValidEmail(edtEmail_for.getTextTrim())){
            authViewModel.getForgotPassword(edtEmail_for.getTextTrim())
            progressBarFor.visible()
        }
    }

}