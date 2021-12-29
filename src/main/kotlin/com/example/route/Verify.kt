package com.example.route

import com.example.models.Hero
import io.ktor.application.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*

fun Route.verify(){
    post("/verify") { //this make the user enter data to make auto to web or another think.
        val request = call.receive<Hero>()
        call.respond(request)
    }
}