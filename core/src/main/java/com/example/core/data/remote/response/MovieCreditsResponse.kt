package com.example.core.data.remote.response

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class MovieCreditsResponse(

    @Json(name = "cast")
    val cast: List<CastItem>,

    @Json(name = "id")
    val id: Int,
)

@JsonClass(generateAdapter = true)
data class CastItem(

    @Json(name = "character")
    val character: String?,

    @Json(name = "original_name")
    val originalName: String?,

    @Json(name = "profile_path")
    val profilePath: String?,

    @Json(name = "id")
    val id: Int?,

    @Json(name = "order")
    val order: Int?
)
