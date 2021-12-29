package com.example.route

import io.ktor.application.*
import io.ktor.response.*
import io.ktor.routing.*

fun Route.redirect(){
    get("/redirect") {
        call.respondRedirect(url = "/moved", permanent = false)
    }
    get("/moved") {
        call.respondText(text = "We have bee Successfully redirect")
    }
}