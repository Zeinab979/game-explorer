package com.app.gameexplorer.domain.repository

import com.app.gameexplorer.domain.model.Game

interface GameRepository {
    suspend fun getGames(
        apiKey: String,
        page: Int,
        genres: String
    ): List<Game>

}