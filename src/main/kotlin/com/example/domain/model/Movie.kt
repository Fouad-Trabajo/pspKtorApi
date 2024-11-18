package com.example.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class Movie(
    val id: Int, val title: String,
    val synopsis: String,
    val duration: Int,
    val genre: List<Genre>,
    val country: List<Country>,
    val year: Int,
    val director: Director,
    val writer: List<Writer>,
    val cast: List<Cast>
)

enum class Genre {
    ADVENTURE, ANIMATION, CRIME, SCI_FI, TERROR, DRAMA, COMEDY, ACTION, FANTASY
}

enum class Country {
    SPAIN, EEUU, UK, FRENCH, GERMANY, ITALY, INDIA, KOREA, JAPAN, ARGENTINA
}

enum class Director {
    DAVID_FINCHER, RIDDLE_SCOOT, MARTIN_SCORSESE
}

enum class Writer {
    WRITER1, WRITER2, WRITER3
}

enum class Cast {
    ACTOR1, ACTOR2, ACTRESS3, ACTRESS4
}