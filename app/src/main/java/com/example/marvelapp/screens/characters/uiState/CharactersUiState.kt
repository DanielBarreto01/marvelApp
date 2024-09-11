package com.example.rickandmortyapp.ui.screens.characters.uiState

import com.example.marvelapp.data.api.models.Character

data class CharactersUiState(
    val characters: List<Character> = emptyList(),
    val isLoading: Boolean = true
)
