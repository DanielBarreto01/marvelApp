package com.example.marvelapp.data.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.room.util.copy
import com.example.marvelapp.config.Config
import com.example.marvelapp.data.api.retrofit.RetrofitService
import com.example.marvelapp.screens.inspectCharacter.uiState.InspectCharacterUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlin.math.log


class InspectCharacterViewModel:ViewModel() {


    fun getCharacterInfo(characterId: Int) {
        viewModelScope.launch {
            val response = retrofitApi.getCharacterById(
                characterId,
                ts = Config.TS,
                apikey = Config.API_KEY,
                hash = Config.HASH,
            )
            Log.d("CharactersViewModel", "API Response hero: $response")
            val newUiState = _uiState.value.copy(
                character = response.data.results[0],
                isCharacterLoading = false
            )
            _uiState.value = newUiState
        }
    }

    private val _uiState = MutableStateFlow(InspectCharacterUiState())
    val uiState: StateFlow<InspectCharacterUiState> = _uiState.asStateFlow()


    private val retrofitApi by lazy {
        RetrofitService.getInstance()
    }
}