package com.example.domain.model

object ManagementStudents {


    private val students = listOf(
        Student(
            1,
            "Pedro",
            "05/06/2000",
            Course.DAM1,
            "pedro@gmail.com",
            listOf(Subject.DDI, Subject.AAD, Subject.PMDM)
        ), Student(
            1,
            "Pepe",
            "12/02/2000",
            Course.DAM1,
            "pepe@gmail.com",
            listOf(Subject.AAD, Subject.PMDM)
        ), Student(
            1,
            "Alicia",
            "05/06/2000",
            Course.DAM1,
            "alicia@gmail.com",
            listOf(Subject.SGE, Subject.DDI)
        )
    )


    fun getStudents(): List<Student> = students

    fun getStudentsByCourse(course: Course) = students.filter {// filter devuelve una lista
        it.course == course
    }

    // devuelve el primer elemento que cumple la condicion
    fun getStudentsByName(name: String) = students.find {
        it.name.equals(name, ignoreCase = true)
    }

    /**
     * Se comprueba si el id viene nulo o si nos lo pasan.
     * Si nos lo pasan se comprueba que no exista ya un alumno con ese id.
     * Si llega nulo se calcula con MAX + 1
     */

    fun newStudent(student: Student) {
        if (student.id != null) {
            val student = getStudentById(student.id)
        }

    }

    // find devuelve un elemento, porque solo puede haber un alumno con ese id en concreto (PK)
    private fun getStudentById(id: Int) = students.find {
        it.id == id
    }
}