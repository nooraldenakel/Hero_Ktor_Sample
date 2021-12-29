package com.example.di

import com.example.repository.HeroRepository
import com.example.repository.HeroRepositoryImpl
import com.example.repository.HeroRepositoryLimit
import com.example.repository.HeroRepositoryLimitImpl
import org.koin.dsl.module

val koinModule = module {
    single<HeroRepository> {
        HeroRepositoryImpl()

    }
    single<HeroRepositoryLimit> {
        HeroRepositoryLimitImpl()
    }
}