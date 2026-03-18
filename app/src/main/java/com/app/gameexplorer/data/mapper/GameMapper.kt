package com.app.gameexplorer.data.mapper

import com.app.gameexplorer.data.remote.dto.GameDto
import com.app.gameexplorer.domain.model.Game

fun GameDto.toDomain(): Game {
    return Game(
        id = id,
        name = name,
        backgroundImage = backgroundImage,
        rating = rating,
        released = released,
        description = description
    )
}