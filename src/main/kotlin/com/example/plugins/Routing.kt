package com.example.plugins

import com.example.domain.model.Aula
import com.example.domain.model.Course
import com.example.domain.model.Genre
import com.example.domain.model.GestionAulas
import com.example.domain.model.ManagementMovies
import com.example.domain.model.ManagementStudents
import com.example.domain.model.Student
import io.ktor.serialization.JsonConvertException
import io.ktor.server.application.Application
import io.ktor.server.http.content.staticResources
import io.ktor.server.request.receive
import io.ktor.server.response.respond
import io.ktor.server.response.respondText
import io.ktor.server.routing.delete
import io.ktor.server.routing.get
import io.ktor.server.routing.post
import io.ktor.server.routing.route
import io.ktor.server.routing.routing

fun Application.configureRouting() {
    routing {
        // Static plugin. Try to access `/static/index.html`
        staticResources("/static", "static")
        get("/") {
            call.respondText("Hello World!")
        }
        get("/psp") {
            call.respondText("<h1>holaa</h1>")
        }

        // Ruta común
        route("/students") { // para no repetir la misma ruta

            get {
                call.respond(ManagementStudents.getStudents())
            }
            get("/course/{course}") {
                val courseTxt = call.parameters["course"]
                val course = Course.valueOf(courseTxt!!)
                val students = ManagementStudents.getStudentsByCourse(course)
                if (students.isEmpty()) {
                    call.respondText("No hay alumnos matriculados en ese curso")
                } else {
                    call.respond(students)
                }
            }

            get("/name/{name}") {
                val name = call.parameters["name"]
                val students = ManagementStudents.getStudentsByName(name!!)
                if (students == null) { //no es una lista, solo devuelve un objeto
                    call.respondText("No hay alumnos con ese nombre")
                } else {
                    call.respond(students)
                }
            }

            // se recoge la llamada post para insertar un nuevo alumno
            post {
                try {
                    val student = call.receive<Student>()
                    val studentInsert = ManagementStudents.newStudent(student)
                    call.respond(studentInsert)
                } catch (jsone: JsonConvertException) {
                    call.respondText("¡Datos inválidos!")
                } catch (e: IllegalStateException) {
                    call.respondText(e.message.toString())
                }
            }

            // Con el navegador, porque no funciona postman en los ordenadores de clase
            get("/deleteStudent/{id}") {
                val id = call.parameters["id"]
                if (id != null) {
                    if (ManagementStudents.deleteStudent(id.toInt())) {
                        call.respondText("Alumno con id $id borrado correctamente")
                    } else {
                        call.respondText("No se puede borrar el alumno con ese id")
                    }
                } else {
                    call.respondText("¡id inválido, campo requerido!")
                }
            }

            // Con postman sería de esta forma
            delete("/deleteStudent") {
                val id = call.receive<Int>()
                if (id != null) {
                    if (ManagementStudents.deleteStudent(id)) {
                        call.respondText("Alumno con id $id borrado correctamente")
                    } else {
                        call.respondText("No existe un alumno con ese id")
                    }
                } else {
                    call.respondText("¡id inválido, campo requerido!")
                }
            }
        }

        route("/movies") {
            get {
                call.respond(ManagementMovies.getMovies())
            }

            get("/genre/{inputGenre}") {
                val genreTMP = call.parameters["inputGenre"]
                if (genreTMP != null) {
                    val genre = Genre.valueOf(genreTMP)
                    call.respond(ManagementMovies.getGenreFromMovies(genre))
                } else {
                    call.respondText("El campo filtrado es obligatorio ")
                }
            }

            get("/year/{inputYear}") {
                val year = call.parameters["inputYear"]
                if (year != null) {
                    val year = year.toInt()
                    call.respond(
                        ManagementMovies.getYearFromMovies(year).filter { it.year == year })
                } else {
                    call.respondText("El campo filtrado es obligatorio ")
                }
            }
        }

        route("/aulas") {

            // Obtener todas las aulas
            get {
                call.respond(GestionAulas.getAulas())
            }

            // Obtener aula por id
            get("/{id}") {
                val id = call.parameters["id"]?.toInt()
                if (id != null) {
                    val aula = GestionAulas.getAulaPorId(id)
                    if (aula.isEmpty()) {
                        call.respondText("No existe un aula con id $id")
                    } else {
                        call.respond(aula)
                    }
                } else {
                    call.respondText("¡id inválido!")
                }
            }

            // Obtener aulas por pabellón
            get("/pabellon/{inputPabellon}") {
                val pabellon = call.parameters["inputPabellon"]
                if (pabellon != null) {
                    val aulas = GestionAulas.getPabellon(pabellon)
                    if (aulas.isEmpty()) {
                        call.respondText("No hay aulas en el pabellón $pabellon")
                    } else {
                        call.respond(aulas)
                    }
                } else {
                    call.respondText("El campo de pabellón es obligatorio")
                }
            }

            // Obtener aula por denominación
            get("/denominacion/{denominacion}") {
                val denominacion = call.parameters["denominacion"]
                if (denominacion != null) {
                    val aula = GestionAulas.getAulaPorDenominacion(denominacion)
                    if (aula == null) {
                        call.respondText("No hay aula con la denominación $denominacion")
                    } else {
                        call.respond(aula)
                    }
                } else {
                    call.respondText("El campo de denominación es obligatorio")
                }
            }

            // Insertar una nueva aula en postMan
            post {
                try {
                    val aula = call.receive<Aula>()
                    val aulaInsert = GestionAulas.nuevaClase(aula)
                    call.respond(aulaInsert)
                } catch (e: IllegalStateException) {
                    call.respondText(e.message.toString())
                } catch (e: Exception) {
                    call.respondText("¡Datos inválidos! ${e.message}")
                }
            }

            // borrar desde el navegador
            get("/deleteAula/{id}") {
                val id = call.parameters["id"]?.toInt()
                if (id != null) {
                    val success = GestionAulas.deleteAula(id)
                    if (success) {
                        call.respondText("Aula con id $id borrada correctamente")
                    } else {
                        call.respondText("No se puede borrar el aula con id $id")
                    }
                } else {
                    call.respondText("¡id inválido!")
                }
            }

            // borrar desde el postman
            delete("/deleteAula") {
                try {
                    val id = call.receive<Int>()
                    val success = GestionAulas.deleteAula(id)
                    if (success) {
                        call.respondText("Aula con id $id borrada correctamente")
                    } else {
                        call.respondText("No existe un aula con id $id")
                    }
                } catch (e: Exception) {
                    call.respondText("¡id inválido! ${e.message}")
                }
            }
        }


        /**
         * Endpoint para obtener todos los estudiantes:
         * GET /students
         *
         * Endpoint para agregar un estudiante:
         * POST /students
         *
         * Endpoint para obtener detalles de un estudiante por su ID:
         * GET /students/{id}
         *
         * Endpoint para borrar un estudiante por su ID:
         * DELETE /students/{id}
         */


    }
}
