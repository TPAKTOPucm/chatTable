package com.example.chat

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.core.app.ActivityCompat
import androidx.fragment.app.commit
import androidx.fragment.app.replace
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.room.Room
import com.example.chat.data.database.Database
import com.example.chat.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private val TAG = "MainActivity: Start method"
    private lateinit var binding : ActivityMainBinding
    lateinit var db: Database

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.S)
//            ActivityCompat.requestPermissions(
//                this,
//                arrayOf(Manifest.permission.),
//                0
//            )
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        Log.i(TAG, "onCreate")
        db = Room.databaseBuilder(
            applicationContext,
            Database::class.java, "Characters"
        ).build()
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
        Intent(applicationContext, ForegroundInfoService::class.java).also {
            it.action = "start"
            startService(it)
        }
        Log.i(TAG, "onPause")
    }

    override fun onResume() {
        super.onResume()
        Intent(applicationContext, ForegroundInfoService::class.java).also {
            it.action = "stop"
            startService(it)
        }
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