package com.example

import io.ktor.application.*
import com.example.plugins.*
import com.example.repository.Database

fun main(args: Array<String>): Unit = io.ktor.server.netty.EngineMain.main(args)

@Suppress("unused")
fun Application.module() {
    Database.init()
    configureKoin()
    configureFreeMarker()//FreeMarker get Template from server.
    configureRouting()
    configureSerialization()
    configureMonitoring()
    configureStatusPages()
    configureDefaultHeader()
}
