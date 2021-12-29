package com.example.route

import io.ktor.application.*
import io.ktor.response.*
import io.ktor.routing.*

fun Route.userQuery(){
    get("/user") {
        val name = call.request.queryParameters["name"]
        val age = call.request.queryParameters["age"]
        call.respondText("Hi my Name is $name and my age is $age")
    }
}