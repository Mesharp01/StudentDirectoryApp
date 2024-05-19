package com.example.studentapp.data

import android.util.Patterns

object StudentHelper {

    fun isValidName(name: String): Boolean
    {
        return name.isNotEmpty() && name.matches("^[A-Za-z ]*$".toRegex())
    }

    fun isValidStudent(name: String, email: String, phoneNum: String) = (isValidName(name) && isValidEmail(email) && isValidPhoneNum(phoneNum))

    fun isValidEmail(email: String): Boolean{
        return email.isNotEmpty() && Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    fun isValidPhoneNum(phoneNum: String): Boolean{
        return phoneNum.isNotEmpty() && Patterns.PHONE.matcher(phoneNum).matches()
    }
}