package com.example

import com.example.auth.JwtService
import com.example.auth.hashKey
import com.example.data.models.Person
import io.ktor.application.*
import com.example.plugins.*
import com.example.repository.Database
import io.ktor.response.*
import io.ktor.routing.*

fun main(args: Array<String>): Unit = io.ktor.server.netty.EngineMain.main(args)

@Suppress("unused")
fun Application.module() {
    Database.init()
    configureKoin()
    val jwtService = JwtService()
    val hashFunction = { password: String -> hashKey(password) }

    routing {
        get("/token") {
            val email = call.request.queryParameters["email"]
            val password = call.request.queryParameters["password"]
            val username = call.request.queryParameters["username"]
            if (email != null && username != null && password != null) {
                val user = Person(
                    userName = username,
                    password = hashFunction(password),
                    email = email
                )
                call.respond(jwtService.generateToken(user = user))
            }

        }
    }

    configureFreeMarker()//FreeMarker get Template from server.
    configureRouting()
    configureSerialization()
    configureMonitoring()
    configureStatusPages()
    configureDefaultHeader()
}
