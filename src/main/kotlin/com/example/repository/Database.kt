package com.example.repository

import com.example.data.table.HeroTable
import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.transactions.transaction

object Database {

    fun init() {
        Database.connect(hikari())
        transaction {
            SchemaUtils.create(HeroTable)
        }
    }

    private fun hikari(): HikariDataSource {
        val config = HikariConfig().also {
            it.apply {
                driverClassName = System.getenv("JDBC_DRIVER")
                jdbcUrl = System.getenv("JDBC_DATABASE_URL")
                maximumPoolSize = 3
                isAutoCommit = false
                transactionIsolation = "TRANSACTION_REPEATABLE_READ"
                validate()
            }
        }
    return HikariDataSource(config)
}

suspend fun <T> dbQuery(block: () -> T): T =
    withContext(Dispatchers.IO) {
        transaction { block() }
    }
}