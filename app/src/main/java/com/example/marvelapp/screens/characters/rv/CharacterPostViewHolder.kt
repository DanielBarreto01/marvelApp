package com.example.rickandmortyapp.ui.screens.characters.rv

import android.util.Log
import androidx.recyclerview.widget.RecyclerView
import com.example.marvelapp.databinding.CharacterViewBinding
import com.example.marvelapp.R
import com.example.marvelapp.data.api.models.Character
import com.example.marvelapp.utils.limitLength
import com.example.marvelapp.utils.loadImage

class CharacterPostViewHolder(
    private val binding: CharacterViewBinding,


): RecyclerView.ViewHolder(binding.root) {
    fun bind(characters: Character) {
        with(binding) {


            tvCharacterName.text = characters.name

            tvDescription.text = tvDescription.context.getString(
                R.string.character_gender,
                characters.description.limitLength(10)
            )

            val imageUrl = "${characters.thumbnail.path}.${characters.thumbnail.extension}"
            Log.d("CharacterPostViewHolder", "Final Image URL: $imageUrl")
            ivCharacterPicture.loadImage(imageUrl)
        }
    }
}