package com.example.route

import io.ktor.application.*
import io.ktor.html.*
import io.ktor.routing.*
import kotlinx.html.*

fun Route.greeting(){
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
}