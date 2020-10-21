package com.example.week3.utils

import android.view.View
import android.widget.EditText
import java.util.regex.Pattern

class View {
    companion object{
        fun View.visible() {
            this.visibility = View.VISIBLE
        }
        fun View.invisible() {
            this.visibility = View.INVISIBLE
        }
        fun isValidEmail(input: String): Boolean {
            return input.trim().isNotEmpty() && Patterns.EMAIL_ADDRESS.matcher(input).matches()
        }

        fun isValidUsername(input: String): Boolean {
            return input.trim().isNotEmpty() && Patterns.USER_NAME.matcher(input).matches()
        }

        fun isValidPassword(input: String): Boolean {
            return input.trim().isNotEmpty() && Patterns.PASSWORD.matcher(input).matches()
        }
        fun EditText.getTextTrim(): String {
            return text.trim().toString()
        }
    }

    object Patterns {

        val EMAIL_ADDRESS: Pattern = Pattern.compile(
            "[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}" +
                    "\\@" +
                    "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}" +
                    "(" +
                    "\\." +
                    "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25}" +
                    ")+"
        )

        val PASSWORD: Pattern = Pattern.compile("^[a-zA-Z0-9_-]{6,30}\$")

        val USER_NAME: Pattern = Pattern.compile("^[a-z0-9_-]{1,30}\$")

        val PHONE_NUMBER: Pattern = Pattern.compile("^\\+?(?:[0-9] ?){6,14}[0-9]\$|[0-9]{8,14}")

        val URL: Pattern =
            Pattern.compile("^((https?|ftp)://|(www|ftp)\\.)?[a-z0-9-]+(\\.[a-z0-9-]+)+([/?].*)?$")
    }
}
