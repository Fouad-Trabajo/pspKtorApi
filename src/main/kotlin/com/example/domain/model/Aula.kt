package com.example.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class Aula(
    var id: Int,
    val denominacion: String,
    val courses: List<Course>,
    val floor: Int,
    val pabellon: String
)
