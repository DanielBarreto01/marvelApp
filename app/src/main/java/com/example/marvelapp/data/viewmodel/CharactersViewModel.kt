package com.example.marvelapp.data.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.marvelapp.config.Config
import com.example.marvelapp.data.api.retrofit.RetrofitService
import com.example.rickandmortyapp.ui.screens.characters.uiState.CharactersUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class CharactersViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(CharactersUiState())
    val uiState: StateFlow<CharactersUiState> = _uiState.asStateFlow()

    private val retrofitApi by lazy {
        RetrofitService.getInstance()
    }

    init {
        viewModelScope.launch {
            getCharacters()
        }
    }

    private suspend fun getCharacters() {
        try {
            val response = retrofitApi.getCharacters(
                ts = Config.TS,
                apikey = Config.API_KEY,
                hash = Config.HASH
            )
            Log.d("CharactersViewModel", "API Response: $response")
            _uiState.value = _uiState.value.copy(
                isLoading = false,
                characters = response.data.results
            )
        } catch (e: Exception) {
            Log.e("CharactersViewModel", "Error fetching characters", e)
        }
    }
}