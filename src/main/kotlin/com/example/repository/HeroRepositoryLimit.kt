package com.example.repository

import com.example.models.ApiResponse
import com.example.models.Hero

interface HeroRepositoryLimit {

    val heroes: List<Hero>
    suspend fun searchHeroes(heroName: String?): ApiResponse
    suspend fun getAllHeroes(page: Int = 1, limit: Int = 4): ApiResponse
}