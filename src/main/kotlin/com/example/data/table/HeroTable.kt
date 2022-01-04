package com.example.data.table

import org.jetbrains.exposed.sql.Table

object HeroTable : Table() {
    val heroId = varchar("id",512)
    val heroName = varchar("name",521)
    val about = varchar("about",521)

    override val primaryKey: PrimaryKey = PrimaryKey(heroId)
}