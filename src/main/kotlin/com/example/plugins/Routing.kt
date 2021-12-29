package com.example.plugins

import com.example.route.*
import io.ktor.application.*
import io.ktor.http.content.*
import io.ktor.routing.*

fun Application.configureRouting() {
    routing {
        root()
//        getAllHeroes()
        getAllHeroesLimit()
        searchHeroes()
        static(remotePath = "images") {//all resource is under the path "assets"
            resources("images")
        }
        greeting()
        pathEndPoint()
        userQuery()
        redirect()
        newPost()
        verify()
    }
}