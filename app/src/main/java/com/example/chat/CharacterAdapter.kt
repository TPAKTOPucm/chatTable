package com.example.chat

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.chat.databinding.CharactersLayoutBinding
import com.example.chat.models.Character

class CharacterAdapter(private var items: Iterable<Character>): RecyclerView.Adapter<CharacterAdapter.CharacterViewHolder>(){

    class CharacterViewHolder(private val binding: CharactersLayoutBinding):
        RecyclerView.ViewHolder(binding.root) {
        fun bind(character: Character) {
            with(binding) {
                name.text = character.name
                born.text = character.born
                culture.text = character.culture
                aliases.text = character.aliases.toString()
                playedBy.text = character.playedBy.toString()
                titles.text = character.titles.toString()
            }
        }

    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder {
        val binding = CharactersLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CharacterViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) {
        holder.bind(items.elementAt(position))
    }

    override fun getItemCount(): Int = items.count()
}