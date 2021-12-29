package com.example.models

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class ApiResponse(
    @SerializedName("success") val success: Boolean? = null,
    @SerializedName("message") val message: String? = null,
    @SerializedName("havePrevPage") val prevPage: Int? = null,
    @SerializedName("haveNextPage") val nextPage: Int? = null,
    @SerializedName("value") val data: List<Hero>? = emptyList(),
    @SerializedName("lastUpdate") val time: Long? = null
)
