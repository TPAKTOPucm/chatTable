package com.example.chat

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import com.google.android.material.textfield.TextInputLayout

class SigninActivity : AppCompatActivity() {
    private val TAG = "SigninActivity: Start method"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signin)
        Log.i(TAG, "onCreate")
        val b : Button = findViewById(R.id.loginButton)
        b.setOnClickListener{
            val login = findViewById<TextInputLayout>(R.id.loginField).editText?.text.toString()
            val password = findViewById<TextInputLayout>(R.id.passwordField).editText?.text.toString()
                if (login(login, password)) {
                val intent = Intent(this, HomeActivity::class.java)
                val email = "test@mail.ru"
                intent.putExtra("name", login)
                intent.putExtra("email", email)
                intent.putExtra("password", password)
                intent.putExtra("user", User(login,email,password))
                startActivity(intent)
            } else
                Toast.makeText(applicationContext, "Неверный логин или пароль", Toast.LENGTH_SHORT).show()
        }
    }

    fun login(login:String, password:String) : Boolean{
        if(login.startsWith("banword") && login == password)
            return true
        return false
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