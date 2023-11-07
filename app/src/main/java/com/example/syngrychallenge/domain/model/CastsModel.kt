package com.example.syngrychallenge.domain.model

import com.squareup.moshi.Json

data class CastsModel(
    val character: String,
    val originalName: String,
    val profilePath: String,
    val id: Int,
    val order: Int

)
