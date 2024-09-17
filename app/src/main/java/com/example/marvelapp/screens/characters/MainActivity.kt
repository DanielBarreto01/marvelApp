package com.example.marvelapp.screens.characters

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.example.marvelapp.databinding.ActivityMainCharactersBinding
import com.example.marvelapp.data.viewmodel.CharactersViewModel
import com.example.marvelapp.screens.inspectCharacter.InspecCharacterActivity
import com.example.marvelapp.screens.inspectCharacter.InspecCharacterActivity.Companion.CHARACTER_ID
import com.example.rickandmortyapp.ui.screens.characters.rv.RVCharactersAdapter
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private val charactersViewModel: CharactersViewModel by viewModels()
    private lateinit var binding: ActivityMainCharactersBinding
    private lateinit var rvCharactersAdapter: RVCharactersAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainCharactersBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initRV()
        initUiStateLifecycle()
    }

    private fun initRV() {
        rvCharactersAdapter = RVCharactersAdapter(
            onInspectCharaterClickListener = { characterId ->
                launchInspectCharacterActivity(characterId)
            }
        )
        binding.rvCharacters.apply {
            layoutManager = GridLayoutManager(this@MainActivity, 3)
            adapter = rvCharactersAdapter
        }
    }
    private fun launchInspectCharacterActivity(characterId: Int) {
        startActivity(
            Intent(
                this,
                InspecCharacterActivity::class.java
            ).apply {
                putExtras(
                    bundleOf(
                        CHARACTER_ID to characterId
                    )
                )
            }
        )
    }

    private fun initUiStateLifecycle() {
        lifecycleScope.launch {
            charactersViewModel.uiState.collect { uiState ->
                uiState.characters.let { charactersList ->
                    rvCharactersAdapter.characters = charactersList
                    rvCharactersAdapter.notifyDataSetChanged()
                }
                binding.rvCharacters.visibility = if (uiState.isLoading) View.INVISIBLE else View.VISIBLE
                binding.pbCharacters.visibility = if (uiState.isLoading.not()) View.INVISIBLE else View.VISIBLE
            }
        }
    }
}