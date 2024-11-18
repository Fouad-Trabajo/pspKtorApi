package com.example.plugins

import com.example.domain.model.Course
import com.example.domain.model.ManagementStudents
import com.example.domain.model.Student
import io.ktor.serialization.JsonConvertException
import io.ktor.server.application.*
import io.ktor.server.http.content.*
import io.ktor.server.request.receive
import io.ktor.server.response.*
import io.ktor.server.routing.*

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

        // Tercera forma de hacerlo

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
