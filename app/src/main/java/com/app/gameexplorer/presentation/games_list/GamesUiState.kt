package com.app.gameexplorer.presentation.games_list

import com.app.gameexplorer.domain.model.Game

sealed interface GamesUiState {
    object Loading : GamesUiState
    data class Success(
        val games: List<Game>,
        val isPaginationLoading: Boolean = false
    ) : GamesUiState
    data class Error(val message: String) : GamesUiState
    object Empty : GamesUiState
}