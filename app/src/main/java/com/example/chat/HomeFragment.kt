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
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.chat.data.moodels.Actor
import com.example.chat.data.moodels.Alias
import com.example.chat.data.moodels.CharacterCoreInfo
import com.example.chat.data.moodels.Title
import com.example.chat.databinding.FragmentHomeBinding
import com.example.chat.models.Character
import com.example.chat.network.KtorNetwork
import com.example.chat.network.NUMBER_AT_GROUP_LIST
import kotlinx.coroutines.launch
import okhttp3.internal.wait


class HomeFragment : Fragment(R.layout.fragment_home) {
    private val TAG = "HomeFragment: Start method"
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    lateinit var navController: NavController
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
        navController = findNavController()
        binding.characterList.layoutManager = LinearLayoutManager(context)
        var characters: List<Character>
        lifecycleScope.launch {
            binding.characterList.adapter = CharacterAdapter(getCharacters())
        }
        binding.settingsButton.setOnClickListener{
            navController.navigate(R.id.action_homeFragment_to_settingsFragment)
        }
        binding.download.setOnClickListener{
            val page = binding.numberField.editText?.text.toString().toIntOrNull() ?: NUMBER_AT_GROUP_LIST
            Log.i("page", page.toString())
            lifecycleScope.launch {
                binding.characterList.adapter = CharacterAdapter(getCharacters(page))
            }
        }
    }

    suspend fun getCharacters(page: Int = NUMBER_AT_GROUP_LIST): List<Character>{
        val activity = activity as MainActivity
        val dao = activity.db.characterDao()
        val dbCharacters = dao.getCharacters(page)
        if (dbCharacters.isEmpty()){
            val characters = KtorNetwork().getCharacters(page)
            try{
                characters.forEach{
                    dao.insertCharacterCoreInfo(CharacterCoreInfo(0,it.name,it.culture,it.born,page))
                    val id = dao.getLastCharacterId()
                    dao.insertActors(it.playedBy.map { Actor(0, it, id) })
                    dao.insertTitles(it.titles.map { Title(0, it, id) })
                    dao.insertAliases(it.aliases.map { Alias(0, it, id) })
                }
            } catch (e: Exception){
                Log.e("db error", e.message.toString())
            }
            return characters
        } else {
            return dbCharacters.map {
                Character(it.character.name, it.character.culture, it.character.born,
                    it.titles.map {
                        it.name
                    },
                    it.aliases.map {
                        it.name
                    },
                    it.actors.map {
                        it.name
                    }
                )
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

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}