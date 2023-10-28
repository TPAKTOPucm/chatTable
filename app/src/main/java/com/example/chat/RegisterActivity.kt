package com.example.chat

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.util.Patterns
import android.widget.Button
import android.widget.Toast
import com.google.android.material.textfield.TextInputLayout
import java.util.regex.Pattern

class RegisterActivity : AppCompatActivity() {
    private val TAG = "RegisterActivity: Start method"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        Log.i(TAG, "onCreate")
        val b : Button = findViewById(R.id.registerButton)
        b.setOnClickListener{
            val login = findViewById<TextInputLayout>(R.id.loginField).editText?.text.toString()
            val password = findViewById<TextInputLayout>(R.id.passwordField).editText?.text.toString()
            val password2 = findViewById<TextInputLayout>(R.id.password2Field).editText?.text.toString()
            val email = findViewById<TextInputLayout>(R.id.emailField).editText?.text.toString()
            if (Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                if (password == password2){
                    val intent = Intent(this, HomeActivity::class.java)
                    intent.putExtra("name", login)
                    intent.putExtra("email", email)
                    intent.putExtra("password", password)
                    intent.putExtra("user", User(login,email,password))
                    startActivity(intent)
                }
                else {
                    Toast.makeText(applicationContext, "Пароли не совпадают", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(applicationContext, "Некорректный email", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onStart() {
        super.onStart()
        Log.i(TAG, "onStart")
    }

    override fun onStop() {
        super.onStop()
        Log.i(TAG,"onStop")
    }

    override fun onPause() {
        super.onPause()
        Log.i(TAG, "onPause")
    }

    override fun onResume() {
        super.onResume()
        Log.i(TAG, "onResume")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.i(TAG, "onDestroy")
    }
    override fun onRestart() {
        super.onRestart()
        Log.i(TAG,"onRestart")
    }
}