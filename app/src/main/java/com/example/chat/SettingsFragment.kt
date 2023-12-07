package com.example.chat

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.os.Environment
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.preferencesKey
import androidx.datastore.preferences.createDataStore
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.example.chat.databinding.FragmentSettingsBinding
import com.example.chat.network.NUMBER_AT_GROUP_LIST
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import java.io.File

const val DEFAULT_CHANEL = "connection_2"
const val DEFAULT_EMAIL = "test@test.ru"

class SettingsFragment : Fragment() {
    private var _binding: FragmentSettingsBinding? = null
    private val binding get() = _binding!!
    lateinit var navController: NavController
    private var _sharedPreferences: SharedPreferences? = null
    private val sharedPreferences get() = _sharedPreferences!!
    private val dataStore get() = _dataStore!!
    private var _dataStore: DataStore<Preferences>? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSettingsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _dataStore = context?.createDataStore(name = "settings")
        _sharedPreferences = context?.getSharedPreferences("settings",Context.MODE_PRIVATE)
        lifecycleScope.launch {
            binding.emailField.setText(dataStore.data.first().get(preferencesKey<String>("email")))
        }
        binding.switch1.isChecked = sharedPreferences.getBoolean("use_connection", false)
        binding.chanel.setText(sharedPreferences.getString("chanel", DEFAULT_CHANEL))
        navController = findNavController()
        binding.back.setOnClickListener{
            navController.navigate(R.id.action_settingsFragment_to_homeFragment)
        }
        binding.delete.setOnClickListener{
            swapFiles(true)
        }
        binding.restore.setOnClickListener{
           swapFiles(false)
        }
    }

    fun swapFiles(hide: Boolean){
        val downloadDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)
        val fileName = "data_${NUMBER_AT_GROUP_LIST}.txt"
        val InternalFile = File(context?.filesDir, fileName)
        val fileFromDownloads = File(downloadDir, fileName)
        if (hide) {
            if (fileFromDownloads.exists()) {
                fileFromDownloads.copyTo(InternalFile, true)
                fileFromDownloads.delete()
            }
        } else {
            if (InternalFile.exists()) {
                InternalFile.copyTo(fileFromDownloads, true)
                InternalFile.delete()
            }
        }
    }

    override fun onPause() {
        super.onPause()
        lifecycleScope.launch {
            dataStore.edit {
                it[preferencesKey<String>("email")] = binding.emailField.text.toString()
            }
        }
        val editor = sharedPreferences.edit()
        editor.putBoolean("use_connection", binding.switch1.isChecked)
        editor.putString("chanel", binding.chanel.text.toString())
        editor.apply()
        _binding = null
    }
}