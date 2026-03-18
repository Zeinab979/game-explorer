package com.app.gameexplorer.domain.use_case

import com.app.gameexplorer.domain.model.Game
import com.app.gameexplorer.domain.repository.GameRepository
import javax.inject.Inject

class GetGamesUseCase @Inject constructor(
    private val repository: GameRepository
) {
    suspend operator fun invoke(apiKey: String, page: Int, genres: String): List<Game> {
        return repository.getGames(apiKey, page, genres)
    }
}