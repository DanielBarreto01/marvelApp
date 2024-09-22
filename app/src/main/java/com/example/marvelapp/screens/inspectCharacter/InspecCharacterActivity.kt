package com.example.marvelapp.screens.inspectCharacter

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.marvelapp.R
import com.example.marvelapp.data.viewmodel.InspectCharacterViewModel
import com.example.marvelapp.databinding.ActivityInspectCharacterBinding
import com.example.marvelapp.screens.inspectCharacter.rv.RvInspectCharacter
import com.example.marvelapp.utils.loadImage
import kotlinx.coroutines.launch

class InspecCharacterActivity : AppCompatActivity() {

    private val inspecCharacterViewModel: InspectCharacterViewModel by viewModels()
    private lateinit var binding: ActivityInspectCharacterBinding
    private lateinit var rvInspectCharacterViewModel: RvInspectCharacter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_inspect_character)
        binding = ActivityInspectCharacterBinding.inflate(layoutInflater)
        setContentView(binding.root)
        getCharacterId()
        initUiStateLifecycle()
        initRv()
        initLists()
    }

    private fun initLists() {
        binding.btnAction2.setOnClickListener {
            val seriesList = inspecCharacterViewModel.uiState.value.Series
            rvInspectCharacterViewModel.updateSeries(seriesList)
        }
        binding.btnAction1.setOnClickListener {
            val comicsList = inspecCharacterViewModel.uiState.value.Comics
            rvInspectCharacterViewModel.updateComics(comicsList)
        }
    }

    private fun initRv() {
        rvInspectCharacterViewModel = RvInspectCharacter()
        binding.rvComicsAndSeries.apply {
            layoutManager = LinearLayoutManager(this@InspecCharacterActivity)
            adapter = rvInspectCharacterViewModel
        }
    }

    private fun getCharacterId() {
        val characterId = intent.extras?.getInt(CHARACTER_ID)
        characterId?.let {
            inspecCharacterViewModel.getCharacterInfo(characterId)
            inspecCharacterViewModel.getCharacterComics(characterId)
            inspecCharacterViewModel.getCharacterSeries(characterId)

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
                            if (uiState.Comics.isNotEmpty()) {
                                rvInspectCharacterViewModel.comics = uiState.Comics
                                rvInspectCharacterViewModel.notifyDataSetChanged()
                            }
                            if (uiState.Series.isNotEmpty()) {
                                rvInspectCharacterViewModel.series = uiState.Series
                            }
                        }
                        pbComicsAndSeries.visibility = if (uiState.isComicsListLoading) View.VISIBLE else View.INVISIBLE
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