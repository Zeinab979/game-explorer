package com.app.gameexplorer.domain.repository

import com.app.gameexplorer.domain.model.Game
import com.app.gameexplorer.domain.util.Resource

interface GameRepository {
    suspend fun getGames(
        apiKey: String,
        page: Int,
        genres: String
    ): Resource<List<Game>>

    suspend fun getGameById(id: Int, apiKey: String): Resource<Game>

}