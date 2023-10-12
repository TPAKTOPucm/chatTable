package com.example.chat

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import com.google.android.material.textfield.TextInputLayout

class SigninActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signin)
        val b : Button = findViewById(R.id.loginButton)
        b.setOnClickListener{
            if (login(findViewById<TextInputLayout>(R.id.loginField).editText?.text.toString(), findViewById<TextInputLayout>(R.id.passwordField).editText?.text.toString())) {
                val intent = Intent(this, HomeActivity::class.java)
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
}