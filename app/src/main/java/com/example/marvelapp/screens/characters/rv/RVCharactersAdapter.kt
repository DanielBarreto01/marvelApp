package com.example.rickandmortyapp.ui.screens.characters.rv

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.marvelapp.data.api.models.Character
import com.example.marvelapp.databinding.CharacterViewBinding
import com.example.marvelapp.utils.isImageNotAvailable

class RVCharactersAdapter(
): RecyclerView.Adapter<CharacterPostViewHolder>() {

    var characters = emptyList<Character>()
        set(value) {
            field = value.filter { character ->
                val imageUrl = "${character.thumbnail.path}.${character.thumbnail.extension}"
                !isImageNotAvailable(imageUrl)
            }
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterPostViewHolder {
        val binding = CharacterViewBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return CharacterPostViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CharacterPostViewHolder, position: Int) {
        holder.bind(characters[position])
    }

    override fun getItemCount(): Int = characters.size
}