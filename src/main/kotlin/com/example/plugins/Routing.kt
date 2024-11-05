package com.example.plugins

import com.example.domain.model.Course
import com.example.domain.model.ManagementStudents
import com.example.domain.model.Student
import com.example.domain.model.Subject
import io.ktor.server.application.*
import io.ktor.server.http.content.*
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


    }
}
