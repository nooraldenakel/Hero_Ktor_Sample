package com.example.repository

import com.example.models.ApiResponse
import com.example.models.Hero
import com.example.route.abilities
import com.example.route.familyList
import com.example.route.natureTypes

class HeroRepositoryLimitImpl : HeroRepositoryLimit {

    override val heroes = listOf(
        Hero(
            id = 1,
            name = "Naruto",
            image = "/images/image1.jpg",
            description = "Boruto: Naruto Next Generations is a Japanese manga series written by Ukyō Kodachi and Masashi Kishimoto, and illustrated by Mikio Ikemoto",
            rating = 7.1,
            power = 6,
            mouth = "8",
            day = "8",
            family = familyList,
            abilities = abilities,
            natureTypes = natureTypes
        ),
        Hero(
            id = 2,
            name = "Sasuke",
            image = "/images/image2.jpg",
            description = "Boruto: Naruto Next Generations is a Japanese manga series written by Ukyō Kodachi and Masashi Kishimoto, and illustrated by Mikio Ikemoto",
            rating = 7.1,
            power = 6,
            mouth = "8",
            day = "8",
            family = familyList,
            abilities = abilities,
            natureTypes = natureTypes
        ),
        Hero(
            id = 3,
            name = "Baruto",
            image = "/images/image3.jpg",
            description = "Boruto: Naruto Next Generations is a Japanese manga series written by Ukyō Kodachi and Masashi Kishimoto, and illustrated by Mikio Ikemoto",
            rating = 7.1,
            power = 6,
            mouth = "8",
            day = "8",
            family = familyList,
            abilities = abilities,
            natureTypes = natureTypes
        ),
    )

    override suspend fun searchHeroes(heroName: String?) = ApiResponse(
        success = true,
        message = "ok",
        data = findHero(nameHero = heroName)
    )

    private fun findHero(nameHero: String?): List<Hero> {
        val heroList = mutableListOf<Hero>()
        return if (!nameHero.isNullOrEmpty()) {
            heroes.forEach { hero ->
                if (hero.name?.lowercase()?.contains(nameHero.lowercase()) == true)
                    heroList.add(hero)
            }
            heroList
        } else {
            emptyList()
        }
    }

    override suspend fun getAllHeroes(page: Int, limit: Int) = ApiResponse(
        success = true,
        message = "Ok",
        prevPage = calculatePage(
            heroes = heroes,
            page = page,
            limit = limit
        )["prevPage"],
        nextPage = calculatePage(
            heroes = heroes,
            page = page,
            limit = limit
        )["nextPage"],
        data = provideHeroes(
            heroes = heroes,
            page = page,
            limit = limit
        ),
        time = System.currentTimeMillis()
    )

    private fun provideHeroes(
        heroes: List<Hero>,
        page: Int,
        limit: Int
    ): List<Hero> {
        val allHeroes = heroes.windowed(
            size = limit,
            step = limit,
            partialWindows = true,
        )
        require(page > 0 && page <= allHeroes.size)
        return allHeroes[page - 1]
    }

    private fun calculatePage(
        heroes: List<Hero>,
        page: Int,
        limit: Int,
    ): Map<String, Int?> {
        val allHeroes = heroes.windowed(
            size = limit,
            step = limit,
            partialWindows = true
        )
        require(page <= allHeroes.size)
        val prevPage = if (page == 1) null else page - 1
        val nextPage = if (page == 5) null else page + 1
        return mapOf("prevPage" to prevPage, "nextPage" to nextPage)
    }
}