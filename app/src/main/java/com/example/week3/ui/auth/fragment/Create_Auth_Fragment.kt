package com.example.week3.ui.auth.fragment

import android.app.DatePickerDialog
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.week3.R
import com.example.week3.ui.main.MainActivity
import com.example.week3.utils.View.Companion.getTextTrim
import com.example.week3.utils.View.Companion.hideKeyboard
import com.example.week3.utils.View.Companion.invisible
import com.example.week3.utils.View.Companion.isValidEmail
import com.example.week3.utils.View.Companion.isValidPassword
import com.example.week3.utils.View.Companion.isValidUsername
import com.example.week3.utils.View.Companion.visible
import com.example.week3.viewmodel.AuthViewModel
import kotlinx.android.synthetic.main.fragment_create__auth_.*
import kotlinx.android.synthetic.main.fragment_login__auth_.*
import java.util.*


class Create_Auth_Fragment : Fragment() {
    lateinit var authViewModel: AuthViewModel

    private var isCheckLogin = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        authViewModel = ViewModelProvider(this).get(AuthViewModel::class.java)
        return inflater.inflate(R.layout.fragment_create__auth_, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        progressBarcreate.invisible()
        notifyerr_res.invisible()
        init()
        getDataRegister()
    }

    private fun getDataRegister() {
        authViewModel.dataRegister?.observe(viewLifecycleOwner, androidx.lifecycle.Observer {
            if (it != null) {
                if (isCheckLogin) {
                    if (it.status) {
                        authViewModel.emailRegister?.value= it.data.email
                        findNavController().navigate(R.id.action_create_Auth_Fragment_to_verify_Auth_Fragment)
                        progressBarcreate.invisible()
                    } else {
                        notifyerr_res.visible()
                        progressBarcreate.invisible()
                        notifyerr_res.text = it.message
                    }
                }
            }
        })
    }

    private fun init() {

        backcreate.setOnClickListener {
            findNavController().popBackStack()
        }
        btn_login_in_register.setOnClickListener {
            findNavController().navigate(R.id.action_create_Auth_Fragment_to_login_Auth_Fragment)
        }
        btnSignUp.setOnClickListener {
            it.hideKeyboard()
            checkRegister()
        }
        edtDate_res.setOnClickListener {
            it.hideKeyboard()
            datePicker()
        }
        edtUsername_res.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                tv_count.text = (30 - p0.toString().length).toString()

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                tv_count.text = (30 - p3).toString()
            }

            override fun afterTextChanged(p0: Editable?) {
            }

        })
    }

    private fun datePicker() {
        var cal = Calendar.getInstance()
        var yyyy = cal.get(Calendar.YEAR)
        var mm = cal.get(Calendar.MONTH)
        var dd = cal.get(Calendar.DAY_OF_MONTH)

        val dpd = DatePickerDialog(
            requireContext(),
            DatePickerDialog.OnDateSetListener { _, yearDl, monthOfYear, dayOfMonth ->
                var date1 = dayOfMonth.toString()
                var month1 = (monthOfYear + 1).toString()
                if (date1.length == 1) date1 = "0$date1"
                if (month1.length == 1) month1 = "0$month1"
                val date = "$date1/$month1/$yearDl"
                edtDate_res.setText(date)
            }, yyyy, mm, dd
        )
        dpd.show()
    }

    private fun checkRegister() {
        if (!isValidEmail(edtEmail_res.getTextTrim()))
            edtEmail_res.error = "Enter a email valid"
        if (!isValidUsername(edtUsername_res.getTextTrim()))
            edtUsername_res.error = "Enter a username valid"
        if (!isValidPassword(edtPass_res.getTextTrim()))
            edtPass_res.error = "Minimum is 6 characters"
        if (!cb_terms_register.isChecked)
            tv_agree.error = "Please read and agree term of service"
        if (edtConfirm_res.getTextTrim() != edtPass_res.getTextTrim()) {
            edtConfirm_res.error = "Password is not macth"
        }
        if (isValidEmail(edtEmail_res.getTextTrim()) && isValidUsername(edtUsername_res.getTextTrim()) && isValidPassword(
                edtPass_res.getTextTrim()
            ) && edtPass_res.getTextTrim() == edtConfirm_res.getTextTrim() && edtDate_res.getTextTrim() != null && cb_terms_register.isChecked
        ) {
            authViewModel.getRegister(
                edtUsername_res.getTextTrim(),
                edtName_res.getTextTrim(),
                edtDate_res.getTextTrim(),
                edtEmail_res.getTextTrim(),
                edtPass_res.getTextTrim(),
                edtConfirm_res.getTextTrim()
            )
            progressBarcreate.visible()
            isCheckLogin = true
        }
    }
}