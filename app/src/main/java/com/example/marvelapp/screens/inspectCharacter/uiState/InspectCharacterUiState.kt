package com.example.marvelapp.screens.inspectCharacter.uiState

import com.example.marvelapp.data.api.models.characters.Character
import com.example.marvelapp.data.api.models.comics.Comic
import com.example.marvelapp.data.api.models.series.Serie

data class InspectCharacterUiState(
    val character: Character? = null,
    val isCharacterLoading: Boolean = true,
    val Comics: List<Comic> = emptyList(),
    val Series: List<Serie> = emptyList(),
    val isComicsListLoading: Boolean = true,
    val isSeriesListLoading: Boolean = true,
)