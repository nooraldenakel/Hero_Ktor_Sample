package com.example.repository

import com.example.data.models.ApiResponse
import com.example.data.models.Hero

interface HeroRepositoryLimit {

    val heroes: List<Hero>
    suspend fun searchHeroes(heroName: String?): ApiResponse
    suspend fun getAllHeroes(page: Int = 1, limit: Int = 4): ApiResponse
}