package com.example.models

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class Hero(
    @SerializedName("id")
    val id: Int? = null,
    @SerializedName("name")
    val name: String? = null,
    @SerializedName("image")
    val image: String? = null,
    @SerializedName("description")
    val description: String? = null,
    @SerializedName("rating")
    val rating: Double? = null,
    @SerializedName("power")
    val power: Int? = null,
    @SerializedName("mouth")
    val mouth: String? = null,
    @SerializedName("day")
    val day: String? = null,
    @SerializedName("family")
    val family: List<String>? = null,
    @SerializedName("abilities")
    val abilities: List<String>? = null,
    @SerializedName("natureTypes")
    val natureTypes: List<String>? = null,
)
