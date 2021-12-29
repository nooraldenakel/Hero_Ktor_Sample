package com.example.plugins

import com.example.models.Hero
import com.example.models.Person
import com.example.route.*
import io.ktor.application.*
import io.ktor.html.*
import io.ktor.http.*
import io.ktor.http.content.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*
import kotlinx.html.*

fun Application.configureRouting() {
    routing {
        root()
//        getAllHeroes()
        getAllHeroesLimit()
        searchHeroes()
        static(remotePath = "images") {//all resource is under the path "assets"
            resources("images")
        }
        get("/welcome") {
            val name = call.request.queryParameters["name"]
            call.respondHtml {
                head {
                    title {
                        +"Custom Title"
                    }
                }
                body {
                    if (name.isNullOrEmpty()) {
                        h3 { +"Welcome!" }
                    } else {
                        h3 { +"Welcome $name" }
                    }
                    p {
                        +"current directory is : ${System.getProperty("user.dir")}"
                    }
                    img {
                        when (name) {
                            "Noor" -> {
                                src = "images/image1.jpg"
                            }
                            "ahmed" -> {
                                src = "images/image2.jpg"
                            }
                            "ali" -> {
                                src = "images/image3.jpg"
                            }
                        }
                    }
                }
            }
        }
        get("/user/{username}") {
            val username = call.parameters["username"]
            val header = call.request.headers["Connection"]
            if (username == "Admin") {
                call.response.header(name = "CustomHeader", username)
                call.respond(message = "Hello Admin $username", status = HttpStatusCode.OK)
            }
            call.respondText("Hello $username with $header")
        }
        get("/user") {
            val name = call.request.queryParameters["name"]
            val age = call.request.queryParameters["age"]
            call.respondText("Hi my Name is $name and my age is $age")
        }
        get("/person") {
            try {
                val person = Person(name = "Noor", age = 27)
                call.respond(message = person, status = HttpStatusCode.OK)
            } catch (e: Exception) {
                call.respond(message = e.message.toString(), status = HttpStatusCode.BadRequest)
            }
        }
        get("/redirect") {
            call.respondRedirect(url = "/moved", permanent = false)
        }
        get("/moved") {
            call.respondText(text = "We have bee Successfully redirect")
        }
        newPost()
        post("/verify") { //this make the user enter data to make auto to web or another think.
            val request = call.receive<Hero>()
            call.respond(request)
        }
    }
}