package com.example.marvelapp.screens.inspectCharacter

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.marvelapp.R
import com.example.marvelapp.data.viewmodel.InspectCharacterViewModel
import com.example.marvelapp.databinding.ActivityInspectCharacterBinding
import com.example.marvelapp.utils.loadImage
import kotlinx.coroutines.launch
import kotlin.math.log

class InspecCharacterActivity : AppCompatActivity() {

    private val inspecCharacterViewModel: InspectCharacterViewModel by viewModels()
    private lateinit var binding: ActivityInspectCharacterBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_inspect_character)
        binding = ActivityInspectCharacterBinding.inflate(layoutInflater)
        setContentView(binding.root)
        getCharacterId()
        initUiStateLifecycle()
    }

    private fun getCharacterId() {
        val characterId = intent.extras?.getInt(CHARACTER_ID)
        characterId?.let {
            inspecCharacterViewModel.getCharacterInfo(characterId)
            Log.d("InspecCharacterActivity", "no llego: $characterId")
        }
    }

   @SuppressLint("NotifyDataSetChanged")
private fun initUiStateLifecycle() {
    lifecycleScope.launch {
        try {
            inspecCharacterViewModel.uiState.collect { uiState ->
                with(binding) {
                    uiState.character?.let { character ->
                        Log.d("InspecCharacterActivity", "heroe: $character")
                        var imageUrl = "${character.thumbnail.path}.${character.thumbnail.extension}"
                        if (imageUrl.startsWith("http:")) {
                            imageUrl = imageUrl.replace("http:", "https:")
                        }

                        Log.d("InspecCharacterActivity", "Final Image URL: $imageUrl")
                        ivCharacterPhoto.loadImage(imageUrl)
                        tvCharacterName.text = character.name
                        tvCharacterDescription.text = character.description
                    }
                    pbCharacter.visibility = if (uiState.isCharacterLoading) View.VISIBLE else View.INVISIBLE
                }
            }
        } catch (e: Exception) {
            Log.e("InspecCharacterActivity", "Exception in initUiStateLifecycle: ${e.message}")
        }
    }
}
    companion object {
        const val CHARACTER_ID = "characterId"
    }
}