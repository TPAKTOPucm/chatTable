package com.example.chat

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.chat.databinding.FragmentHomeBinding
import com.example.chat.models.Character
import com.example.chat.network.KtorNetwork
import com.example.chat.network.NetworkApi
import kotlinx.coroutines.launch
import okhttp3.internal.wait


class HomeFragment : Fragment(R.layout.fragment_home) {
    private val TAG = "HomeFragment: Start method"
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "onCreate")
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.characterList.layoutManager = LinearLayoutManager(context)
        var characters: List<Character>
        lifecycleScope.launch {
            characters = KtorNetwork().getCharacters()
            binding.characterList.adapter = CharacterAdapter(characters)
        }
        binding.textGreeting.text = "Привет, ${arguments?.getString("name")}!"
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