package com.werewolfstudiios.amphibians.ui.screens


sealed interface AmphibianUiState {
    data class Success(val amphibians: String)
    object Error : AmphibianUiState
    object Loading : AmphibianUiState
}