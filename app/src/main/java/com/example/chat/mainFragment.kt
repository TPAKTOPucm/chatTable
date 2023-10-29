package com.example.chat

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button


class mainFragment : Fragment(R.layout.fragment_main) {

    private val TAG = "MainFragment: Start method"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "onCreate")
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = super.onCreateView(inflater, container, savedInstanceState)
        val enterButton : Button = view!!.findViewById(R.id.enter_button)
        enterButton.setOnClickListener{
            MainActivity.navController.navigate(R.id.action_mainFragment_to_singinFragment)
        }
        val registerButton : Button = view.findViewById(R.id.register_button)
        registerButton.setOnClickListener{
            MainActivity.navController.navigate(R.id.action_mainFragment_to_registerFragment)
        }
        return view
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