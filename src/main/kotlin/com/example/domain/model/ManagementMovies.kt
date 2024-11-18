package com.example.domain.model

object ManagementMovies {

    private val movies = mutableListOf(
        Movie(
            id = 1,
            title = "Inception",
            synopsis = "A thief who steals corporate secrets through the use of dream-sharing technology.",
            duration = 148,
            genre = listOf(Genre.ACTION, Genre.SCI_FI),
            country = listOf(Country.EEUU),
            year = 2010,
            director = Director.DAVID_FINCHER,
            writer = listOf(Writer.WRITER1),
            cast = listOf(Cast.ACTOR1, Cast.ACTOR2)
        ),
        Movie(
            id = 2,
            title = "The Dark Knight",
            synopsis = "Batman faces the Joker in Gotham City.",
            duration = 152,
            genre = listOf(Genre.ACTION, Genre.DRAMA),
            country = listOf(Country.EEUU),
            year = 2008,
            director = Director.DAVID_FINCHER,
            writer = listOf(Writer.WRITER2),
            cast = listOf(Cast.ACTOR1, Cast.ACTRESS3)
        ),
        Movie(
            id = 3,
            title = "Avengers: Endgame",
            synopsis = "The Avengers assemble once again to undo the damage caused by Thanos.",
            duration = 181,
            genre = listOf(Genre.ACTION, Genre.FANTASY),
            country = listOf(Country.EEUU),
            year = 2019,
            director = Director.MARTIN_SCORSESE,
            writer = listOf(Writer.WRITER3),
            cast = listOf(Cast.ACTOR2, Cast.ACTRESS4)
        ),
        Movie(
            id = 4,
            title = "The Godfather",
            synopsis = "The aging patriarch transfers control of his crime empire to his reluctant son.",
            duration = 175,
            genre = listOf(Genre.DRAMA),
            country = listOf(Country.ITALY),
            year = 1972,
            director = Director.RIDDLE_SCOOT,
            writer = listOf(Writer.WRITER2),
            cast = listOf(Cast.ACTOR1, Cast.ACTOR2)
        ),
        Movie(
            id = 5,
            title = "Forrest Gump",
            synopsis = "A man with an extraordinary life story faces challenges in a turbulent era.",
            duration = 142,
            genre = listOf(Genre.DRAMA),
            country = listOf(Country.EEUU),
            year = 1994,
            director = Director.RIDDLE_SCOOT,
            writer = listOf(Writer.WRITER1),
            cast = listOf(Cast.ACTOR1, Cast.ACTRESS3)
        ),
        Movie(
            id = 6,
            title = "The Matrix",
            synopsis = "A hacker learns about the true nature of his reality and his role in the war against controllers.",
            duration = 136,
            genre = listOf(Genre.ACTION, Genre.SCI_FI),
            country = listOf(Country.EEUU),
            year = 1999,
            director = Director.MARTIN_SCORSESE,
            writer = listOf(Writer.WRITER3),
            cast = listOf(Cast.ACTOR2, Cast.ACTRESS4)
        ),
        Movie(
            id = 7,
            title = "Gladiator",
            synopsis = "A general seeks vengeance against the emperor who murdered his family.",
            duration = 155,
            genre = listOf(Genre.ACTION, Genre.DRAMA),
            country = listOf(Country.ITALY),
            year = 2000,
            director = Director.RIDDLE_SCOOT,
            writer = listOf(Writer.WRITER2),
            cast = listOf(Cast.ACTOR1, Cast.ACTOR2)
        ),
        Movie(
            id = 8,
            title = "Pulp Fiction",
            synopsis = "The lives of several characters intertwine in four stories of violence and redemption.",
            duration = 154,
            genre = listOf(Genre.CRIME, Genre.DRAMA),
            country = listOf(Country.EEUU),
            year = 1994,
            director = Director.DAVID_FINCHER,
            writer = listOf(Writer.WRITER3),
            cast = listOf(Cast.ACTRESS3, Cast.ACTOR2)
        ),
        Movie(
            id = 9,
            title = "The Shawshank Redemption",
            synopsis = "Two imprisoned men bond over years, finding solace in common decency.",
            duration = 142,
            genre = listOf(Genre.DRAMA),
            country = listOf(Country.EEUU),
            year = 1994,
            director = Director.RIDDLE_SCOOT,
            writer = listOf(Writer.WRITER1),
            cast = listOf(Cast.ACTOR1, Cast.ACTOR2)
        ),
        Movie(
            id = 10,
            title = "The Lion King",
            synopsis = "A lion prince and his father are targeted by his uncle, who wants to ascend the throne.",
            duration = 88,
            genre = listOf(Genre.ANIMATION, Genre.ADVENTURE),
            country = listOf(Country.EEUU),
            year = 1994,
            director = Director.MARTIN_SCORSESE,
            writer = listOf(Writer.WRITER2),
            cast = listOf(Cast.ACTRESS3, Cast.ACTOR2)
        )
    )

    fun getMovies(): List<Movie> = movies

    fun getGenreFromMovies(genre: Genre) = movies.filter {
        it.genre.contains(genre)
    }

    fun getYearFromMovies(year: Int) = movies.filter {
        it.year == year
    }
}