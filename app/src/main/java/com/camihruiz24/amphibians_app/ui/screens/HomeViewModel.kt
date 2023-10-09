package com.camihruiz24.amphibians_app.ui.screens

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.camihruiz24.amphibians_app.data.Amphibian
import com.camihruiz24.amphibians_app.data.AmphibianRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class HomeViewModel @Inject constructor(
    private val amphibianRepository: AmphibianRepository
) : ViewModel() {
    var uiState by mutableStateOf<UiState<List<Amphibian>>>(UiState.Loading)
        private set

    init {
        getRemoteInfo()
    }

    private fun getRemoteInfo() {
        viewModelScope.launch {
            amphibianRepository.getAmphibiansInfo()
                .onSuccess {
                    uiState = UiState.Success(it)
                }
                .onFailure {
                    Log.d("viewModel Exception", "$it")
                    uiState = UiState.Error(it)
                }
        }
    }

    fun retry(){
        uiState = UiState.Loading
        getRemoteInfo()
    }
}