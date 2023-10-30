package com.example.chat

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.fragment.app.commit
import androidx.fragment.app.replace

class MainActivity : AppCompatActivity() {
    private val TAG = "MainActivity: Start method"
    companion object{
        var MAIN : MainActivity? = null
    }

    private fun Lab2Method(){
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

    private fun Lab3Method(){
        if (MAIN == null)
            MAIN = this

    }

    public fun navigateToRegister(){
        supportFragmentManager.commit {
            replace<RegisterFragment>(R.id.fragmentContainerView)
            setReorderingAllowed(true)
            addToBackStack(null)
        }
    }
    public fun navigateToSingin(){
        supportFragmentManager.commit {
            replace<SinginFragment>(R.id.fragmentContainerView)
            setReorderingAllowed(true)
            addToBackStack(null)
        }
    }
    public fun navigateToHome(args: Bundle?){
        supportFragmentManager.commit {
            replace<HomeFragment>(R.id.fragmentContainerView, args = args)
            setReorderingAllowed(true)
            addToBackStack(null)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.i(TAG, "onCreate")
        Lab3Method()
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