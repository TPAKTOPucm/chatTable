package com.example.chat

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.example.chat.databinding.FragmentSinginBinding
import com.example.chat.models.User

class SinginFragment : Fragment(R.layout.fragment_singin) {
    private val TAG = "MainFragment: Start method"
    private var _binding: FragmentSinginBinding? = null
    lateinit var navController: NavController
    private val binding get() = _binding!!
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "onCreate")
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSinginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = findNavController()
        val b : Button = binding.loginButton
        b.setOnClickListener{
            val login = binding.loginField.editText?.text.toString()
            val password = binding.passwordField.editText?.text.toString()
            if (login(login, password)) {
                val email = "test@mail.ru"
                val bundle = bundleOf("name" to login,"email"  to email, "password" to password, "user" to User(login,email,password))
                navController.navigate(R.id.action_singinFragment_to_homeFragment, bundle)
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

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}