package com.example.chat

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.core.os.bundleOf
import com.google.android.material.textfield.TextInputLayout

class SinginFragment : Fragment(R.layout.fragment_singin) {
    private val TAG = "MainFragment: Start method"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "onCreate")
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val b : Button = view.findViewById(R.id.loginButton)
        b.setOnClickListener{
            val login = view.findViewById<TextInputLayout>(R.id.loginField).editText?.text.toString()
            val password = view.findViewById<TextInputLayout>(R.id.passwordField).editText?.text.toString()
            if (login(login, password)) {
                val email = "test@mail.ru"
                val bundle = bundleOf("name" to login,"email"  to email, "password" to password, "user" to User(login,email,password))
                MainActivity.MAIN!!.navigateToHome(bundle)
            } else
                Toast.makeText(context, "Неверный логин или пароль", Toast.LENGTH_SHORT).show()
        }
    }

    private fun login(login:String, password:String) : Boolean{
        if(login.startsWith("l") && login == password)
            return true
        return false
    }

    override fun onStop() {
        super.onStop()
        Log.d(TAG, "onStop")
    }

    override fun onStart() {
        super.onStart()
        Log.d(TAG, "onStart")
    }

    override fun onPause() {
        super.onPause()
        Log.d(TAG, "onPause")
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG, "onResume")
    }
}