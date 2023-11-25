package com.example.chat

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
import androidx.navigation.NavController
import com.example.chat.databinding.FragmentRegisterBinding
import com.example.chat.models.User

class RegisterFragment : Fragment(R.layout.fragment_register) {
    private val TAG = "RegisterFragment: Start method"
    private lateinit var binding: FragmentRegisterBinding
    lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "onCreate")
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRegisterBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val b : Button = binding.registerButton
        b.setOnClickListener{
            val login = binding.loginField.editText?.text.toString()
            val password = binding.passwordField.editText?.text.toString()
            val password2 = binding.password2Field.editText?.text.toString()
            val email = binding.emailField.editText?.text.toString()
            if (Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                if (password == password2){
                    val bundle = bundleOf("name" to login,"email"  to email, "password" to password, "user" to User(login,email,password))
                    navController.navigate(R.id.action_registerFragment_to_homeFragment, bundle)
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