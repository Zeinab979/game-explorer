package com.app.gameexplorer.data.remote

import com.app.gameexplorer.data.remote.dto.GameResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("games")
    suspend fun getGames(
        @Query("key") apiKey: String,
        @Query("page") page: Int,
        @Query("genres") genres: String
    ): GameResponse

}