package com.example.data.models

import kotlinx.serialization.Serializable

@Serializable
data class Person(
    val userName: String,
    val password: String,
    val email: String
)