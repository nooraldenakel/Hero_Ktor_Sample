package com.example.repository

import com.example.models.ApiResponse
import com.example.models.Hero
import com.example.route.abilities
import com.example.route.familyList
import com.example.route.natureTypes

class HeroRepositoryImpl : HeroRepository {

    override val heroes: Map<Int, List<Hero>> by lazy {
        mapOf(
            1 to page1,
            2 to page2,
            3 to page3,
            4 to page4,
            5 to page5,
        )
    }
    override val page1: List<Hero> = listOf(
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
    override val page2: List<Hero> = listOf()
    override val page3: List<Hero> = listOf()
    override val page4: List<Hero> = listOf()
    override val page5: List<Hero> = listOf()

    override suspend fun getAllHeroes(page: Int): ApiResponse {
        return ApiResponse(
            success = true,
            message = "ok",
            prevPage = calculatePage(page = page)["havePrevPage"],
            nextPage = calculatePage(page = page)["haveNextPage"],
            data = heroes[page]
        )
    }

    private fun calculatePage(page: Int): Map<String, Int?> {
        var prevPage: Int? = page
        var nextPage: Int? = page

        if (page in 1..4) {
            nextPage = nextPage?.plus(1)
        }
        if (page in 2..5) {
            prevPage = prevPage?.minus(1)
        }
        if (page == 1) {
            prevPage = null
        }
        if (page == 5) {
            nextPage = null
        }
        return mapOf("havePrevPage" to prevPage, "haveNextPage" to nextPage)
    }

    override suspend fun searchHeroes(heroName:String?): ApiResponse {
        return ApiResponse(
            success = true,
            message = "ok",
            data = findHero(nameHero = heroName)
        )
    }

    private fun findHero(nameHero: String?): List<Hero> {
        val heroList = mutableListOf<Hero>()
        return if (!nameHero.isNullOrEmpty()) {
            heroes.forEach { (_, heroes) ->
                heroes.forEach { hero ->
                    if (hero.name?.lowercase()?.contains(nameHero.lowercase()) == true)
                        heroList.add(hero)
                }
            }
            heroList
        } else {
            emptyList()
        }
    }
}