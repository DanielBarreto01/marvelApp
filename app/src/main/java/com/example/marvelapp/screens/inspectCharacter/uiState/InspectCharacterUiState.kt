package com.example.marvelapp.screens.inspectCharacter.uiState

import com.example.marvelapp.data.api.models.Character

data class InspectCharacterUiState(
    val character: Character? = null,
    val isCharacterLoading: Boolean = true,
    val isEpisodeListLoading: Boolean = true
)