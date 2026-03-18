package com.app.gameexplorer.data.remote.dto

import com.google.gson.annotations.SerializedName

data class GameResponse(
    @SerializedName("results")
    val results: List<GameDto>
)

data class GameDto(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("background_image")
    val backgroundImage: String,
    @SerializedName("rating")
    val rating: Double,
    @SerializedName("released")
    val released: String?
)