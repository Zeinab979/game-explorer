package com.app.gameexplorer.domain.use_case

import com.app.gameexplorer.BuildConfig
import com.app.gameexplorer.domain.model.Game
import com.app.gameexplorer.domain.repository.GameRepository
import com.app.gameexplorer.domain.util.Resource
import javax.inject.Inject

class GetGameDetailUseCase @Inject constructor(
    private val repository: GameRepository
) {
    suspend operator fun invoke(gameId: Int): Resource<Game> {
        return repository.getGameById(gameId, BuildConfig.RAWG_API_KEY)
    }
}