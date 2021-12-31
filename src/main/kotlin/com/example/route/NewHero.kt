package com.example.route

import com.example.models.ApiResponse
import com.example.models.Hero
import io.ktor.application.*
import io.ktor.http.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*

val familyList = mutableListOf(
    "naruto","hinata",
)
val abilities = mutableListOf(
    "Jump","Fly","Speed",
)
val natureTypes = mutableListOf(
    "Wind","Light",
)
val heroList = mutableListOf(
    Hero(
        id = 1,
        name = "Baruto",
        image = "https://m.media-amazon.com/images/M/MV5BMDFjYTc1ODgtNjRlNi00ZDllLTg3ZGYtMjJlYTA0NjBkYWZlXkEyXkFqcGdeQXRyYW5zY29kZS13b3JrZmxvdw@@._V1_.jpg",
        about = "Boruto: Naruto Next Generations is a Japanese manga series written by Ukyō Kodachi and Masashi Kishimoto, and illustrated by Mikio Ikemoto",
        rating = 9.1,
        power = 9,
        month = "6",
        day = "10",
        family = familyList,
        abilities = abilities,
        natureTypes = natureTypes
    ),
    Hero(
        id = 2,
        name = "Naruto",
        image = "https://m.media-amazon.com/images/M/MV5BMDFjYTc1ODgtNjRlNi00ZDllLTg3ZGYtMjJlYTA0NjBkYWZlXkEyXkFqcGdeQXRyYW5zY29kZS13b3JrZmxvdw@@._V1_.jpg",
        about = "Boruto: Naruto Next Generations is a Japanese manga series written by Ukyō Kodachi and Masashi Kishimoto, and illustrated by Mikio Ikemoto",
        rating = 7.1,
        power = 6,
        month = "8",
        day = "8",
        family = familyList,
        abilities = abilities,
        natureTypes = natureTypes
    ),
)

fun Route.newPost() {
    get("/hero") {
        call.respond(
            ApiResponse(
                true,
                message = "",
                prevPage = 0,
                nextPage = 1,
                data = heroList
            )
        )
    }
    post("/hero/new") {
        val params = call.receiveParameters()
        val id = params["id"]?.toInt()
        val name = params["name"]
        val image = params["image"]
        val description = params["description"]
        val rating = params["rating"]?.toDouble()
        val power = params["power"]?.toInt()
        val mouth = params["mouth"]
        val day = params["day"]
        if (id != null && name != null) {
            val newHero = Hero(id, name, image, description, rating, power, mouth, day)
            heroList.add(newHero)
            call.respond(HttpStatusCode.Created)
        }
        else{
            call.respond(HttpStatusCode.BadRequest,"Not Found")
        }
    }
}