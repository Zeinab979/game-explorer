package com.app.gameexplorer.domain.model

data class GameDetails(
    val id: Int,
    val name: String,
    val description: String,
    val backgroundImage: String,
    val rating: Double,
    val released: String
)