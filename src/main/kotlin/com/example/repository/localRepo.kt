package com.example.repository

import com.example.data.models.Hero
import com.example.data.table.HeroTable
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.select

class localRepo {
    suspend fun insertHero(hero: Hero){
        Database.dbQuery {
            HeroTable.insert { table ->
                table[HeroTable.heroId] = hero.id.toString()
                table[HeroTable.heroName] = hero.name.toString()
                table[HeroTable.about] = hero.about.toString()
            }
        }
    }

    suspend fun findHeroById (heroId : String) = Database.dbQuery{
        HeroTable.select {
            HeroTable.heroId.eq(heroId)
        }
            .map { rowToHero(it) }
            .singleOrNull()
    }

    private fun rowToHero(row:ResultRow?):Hero?{
        return if (row != null){
            Hero(
                id = row[HeroTable.heroId],
                name = row[HeroTable.heroName],
                about = row[HeroTable.about]
            )
        }else null
    }
}