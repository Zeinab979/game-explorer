package com.app.gameexplorer.data.repository

import com.app.gameexplorer.data.mapper.toDomain
import com.app.gameexplorer.data.remote.ApiService
import com.app.gameexplorer.domain.model.Game
import com.app.gameexplorer.domain.repository.GameRepository
import com.app.gameexplorer.domain.util.Resource
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GameRepositoryImpl @Inject constructor(
    private val apiService: ApiService
) : GameRepository {

    override suspend fun getGames(
        apiKey: String,
        page: Int,
        genres: String
    ): Resource<List<Game>> {
        return try {
            val response = apiService.getGames(apiKey, page, genres)
            Resource.Success(response.results.map { it.toDomain() })
        } catch (e: HttpException) {
            Resource.Error(message = "Server Error: ${e.code()}")
        } catch (e: IOException) {
            Resource.Error(message = "Check your internet connection")
        } catch (e: Exception) {
            Resource.Error(message = e.localizedMessage ?: "An unexpected error occurred")
        }
    }

    override suspend fun getGameById(id: Int, apiKey: String): Resource<Game> {
        return try {
            val response = apiService.getGameDetail(id, apiKey)
            Resource.Success(response.toDomain())
        } catch (e: Exception) {
            Resource.Error(message = "Could not load game details")
        }
    }
}
