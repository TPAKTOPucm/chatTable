package com.example.chat

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.util.Patterns
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.core.os.bundleOf
import com.google.android.material.textfield.TextInputLayout

class RegisterFragment : Fragment(R.layout.fragment_register) {
    private val TAG = "RegisterFragment: Start method"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "onCreate")
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val b : Button = view.findViewById(R.id.registerButton)
        b.setOnClickListener{
            val login = view.findViewById<TextInputLayout>(R.id.loginField).editText?.text.toString()
            val password = view.findViewById<TextInputLayout>(R.id.passwordField).editText?.text.toString()
            val password2 = view.findViewById<TextInputLayout>(R.id.password2Field).editText?.text.toString()
            val email = view.findViewById<TextInputLayout>(R.id.emailField).editText?.text.toString()
            if (Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                if (password == password2){
                    val bundle = bundleOf("name" to login,"email"  to email, "password" to password, "user" to User(login,email,password))
                    MainActivity.MAIN!!.navigateToHome(bundle)
                }
                else {
                    Toast.makeText(context, "Пароли не совпадают", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(context, "Некорректный email", Toast.LENGTH_SHORT).show()
            }
        }
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