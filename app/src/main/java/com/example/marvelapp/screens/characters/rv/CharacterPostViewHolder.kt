package com.example.rickandmortyapp.ui.screens.characters.rv

import android.util.Log
import androidx.recyclerview.widget.RecyclerView
import com.example.marvelapp.databinding.CharacterViewBinding
import com.example.marvelapp.data.api.models.characters.Character
import com.example.marvelapp.utils.loadImage

class CharacterPostViewHolder(
    private val binding: CharacterViewBinding,
    private val onInspectCharaterClickListener: (id: Int) -> Unit

): RecyclerView.ViewHolder(binding.root) {
    fun bind(characters: Character) {
        with(binding) {
            tvCharacterName.text = characters.name

            ivCharacterPicture.setOnClickListener {
                onInspectCharaterClickListener(characters.id)
            }

            var imageUrl = "${characters.thumbnail.path}.${characters.thumbnail.extension}"
            if (imageUrl.startsWith("http:")) {
                imageUrl = imageUrl.replace("http:", "https:")
            }

            Log.d("CharacterPostViewHolder", "Final Image URL: $imageUrl")
            ivCharacterPicture.loadImage(imageUrl)
        }
    }
}