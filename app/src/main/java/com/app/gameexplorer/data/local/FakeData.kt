package com.app.gameexplorer.data.local

import com.app.gameexplorer.domain.model.Game

object FakeData {
    val fakeGames = listOf(
        Game(
            id = 1,
            name = "The Witcher 3: Wild Hunt",
            backgroundImage = "https://media.rawg.io/media/games/618/618c47b6e3b32066f11401aa85162fa2.jpg",
            rating = 4.67,
            released = "2015-05-18",
            description = "The Witcher: Wild Hunt is a story-driven, next-generation open world role-playing game set in a visually stunning fantasy universe full of meaningful choices and impactful consequences."
        ),
        Game(
            id = 2,
            name = "Grand Theft Auto V",
            backgroundImage = "https://media.rawg.io/media/games/20a/20a43573f4fed93e0ad0663a878b3d74.jpg",
            rating = 4.47,
            released = "2013-09-17",
            description = "Grand Theft Auto V for PC offers players the option to explore the award-winning world of Los Santos and Blaine County in resolutions of up to 4k and beyond."
        )
    )
}