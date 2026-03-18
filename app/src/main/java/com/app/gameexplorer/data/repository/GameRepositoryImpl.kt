package com.app.gameexplorer.data.repository

import com.app.gameexplorer.data.mapper.toDomain
import com.app.gameexplorer.data.remote.ApiService
import com.app.gameexplorer.domain.model.Game
import com.app.gameexplorer.domain.repository.GameRepository
import javax.inject.Inject

class GameRepositoryImpl @Inject constructor(
    private val apiService: ApiService
) : GameRepository {

    override suspend fun getGames(
        apiKey: String,
        page: Int,
        genres: String
    ): List<Game> {
        val response = apiService.getGames(apiKey, page, genres)
        return response.results.map { it.toDomain() }
    }
}