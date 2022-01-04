package com.example.repository

import com.example.data.models.ApiResponse
import com.example.data.models.Hero

interface HeroRepository {
    val heroes: Map<Int, List<Hero>>

    val page1: List<Hero>
    val page2: List<Hero>
    val page3: List<Hero>
    val page4: List<Hero>
    val page5: List<Hero>

    suspend fun searchHeroes(heroName:String?): ApiResponse
    suspend fun getAllHeroes(page: Int = 1): ApiResponse
}