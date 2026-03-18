package com.app.gameexplorer.presentation.game_details

import com.app.gameexplorer.domain.model.Game

sealed class GameDetailState {
    object Loading : GameDetailState()
    data class Success(val game: Game) : GameDetailState()
    data class Error(val message: String) : GameDetailState()
}