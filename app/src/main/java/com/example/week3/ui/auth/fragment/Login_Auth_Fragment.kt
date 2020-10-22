package com.example.week3.ui.auth.fragment

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.week3.R
import com.example.week3.network.getFcmToken
import com.example.week3.ui.main.MainActivity
import com.example.week3.utils.Constants.Companion.ACCESS_TOKEN
import com.example.week3.utils.Constants.Companion.EMAIL
import com.example.week3.utils.Constants.Companion.FCM_TOKEN
import com.example.week3.utils.Constants.Companion.FIRST_TIME
import com.example.week3.utils.Constants.Companion.PASSWORD
import com.example.week3.utils.Constants.Companion.PREFS_NAME
import com.example.week3.utils.View.Companion.getTextTrim
import com.example.week3.utils.View.Companion.hideKeyboard
import com.example.week3.utils.View.Companion.invisible
import com.example.week3.utils.View.Companion.isValidEmail
import com.example.week3.utils.View.Companion.isValidPassword
import com.example.week3.utils.View.Companion.visible
import com.example.week3.viewmodel.AuthViewModel
import kotlinx.android.synthetic.main.fragment_login__auth_.*

class Login_Auth_Fragment : Fragment() {
    lateinit var authViewModel: AuthViewModel
    private var isCheckLogin = false
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        authViewModel = ViewModelProvider(this).get(AuthViewModel::class.java)
        return  inflater.inflate(R.layout.fragment_login__auth_, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        progressBar.invisible()
        notifyerr.invisible()

        btnLogin.setOnClickListener {
            it.hideKeyboard()
            checkLogin() }
        closelogin.setOnClickListener {
            findNavController().popBackStack()
        }
        forgotpass.setOnClickListener{
            findNavController().navigate(R.id.action_login_Auth_Fragment_to_forgotPasswword_Auth_Fragment)
        }
        dataLogin()
    }
    private fun dataLogin(){
        authViewModel.dataLogin?.observe(viewLifecycleOwner, Observer {
            if(it!=null) {
                if (isCheckLogin) {
                    progressBar.invisible()
                    if (it?.status) {
                        val pref: SharedPreferences = requireContext().getSharedPreferences(PREFS_NAME, AppCompatActivity.MODE_PRIVATE)
                        val editor = pref.edit()
                        editor.putBoolean(FIRST_TIME, false)
                        editor.putString(ACCESS_TOKEN,it.data.accessToken)
                        editor.putString(EMAIL,edtEmail.getTextTrim())
                        editor.putString(PASSWORD,edtPass.getTextTrim())
                        editor.apply()
                        findNavController().navigate(R.id.action_login_Auth_Fragment_to_interestFragment)
                    } else {
                        notifyerr.visible()
                    }
                }
            }

        })
    }
    private fun checkLogin() {

        if (!isValidEmail(edtEmail.getTextTrim()))
            edtEmail.error = "Enter a valid email"
        if (!isValidPassword(edtPass.getTextTrim()))
            edtPass.error = "Enter a valid password"
        else if (isValidEmail(edtEmail.getTextTrim()) &&
            isValidPassword(edtPass.getTextTrim())
        ) {
            progressBar.visible()

            authViewModel.getLogin(edtEmail.getTextTrim(),edtPass.getTextTrim(),FCM_TOKEN+ getFcmToken())
            isCheckLogin=true
        }
    }
}
