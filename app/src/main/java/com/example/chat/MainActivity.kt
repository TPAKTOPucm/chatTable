package com.example.chat

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.navigation.NavController
import androidx.navigation.Navigation

class MainActivity : AppCompatActivity() {
    private val TAG = "MainActivity: Start method"
    companion object{
        lateinit var navController : NavController
    }

    private fun secondLabMethod(){
        val enterButton : Button = findViewById(R.id.enter_button)
        enterButton.setOnClickListener{
            val intent = Intent(this, SigninActivity::class.java)
            startActivity(intent)
        }
        val registerButton : Button = findViewById(R.id.register_button)
        registerButton.setOnClickListener{
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }
    }

    private fun thirdLabMethod(){
        navController = Navigation.findNavController(this,R.id.nav_host_fragment)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.i(TAG, "onCreate")
        thirdLabMethod()
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