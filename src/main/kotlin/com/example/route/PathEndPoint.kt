package com.example.route

import io.ktor.application.*
import io.ktor.http.*
import io.ktor.response.*
import io.ktor.routing.*


fun Route.pathEndPoint(){
    get("/user/{username}") {
        val username = call.parameters["username"]
        val header = call.request.headers["Connection"]
        if (username == "Admin") {
            call.response.header(name = "CustomHeader", username)
            call.respond(message = "Hello Admin $username", status = HttpStatusCode.OK)
        }
        call.respondText("Hello $username with $header")
    }
}