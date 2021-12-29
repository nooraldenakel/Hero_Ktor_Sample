package com.example.route

import io.ktor.application.*
import io.ktor.http.*
import io.ktor.response.*
import io.ktor.routing.*

fun Route.root() {
    get("/") {
        call.respond(
            message = "Hello From Naruto Api",
            status = HttpStatusCode.OK
        )
    }
}