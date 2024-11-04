package com.example.domain.model

object ManagementStudents {


    private val students = listOf(
        Student(
            1, "Pedro", "05/06/2000", Course.DAM1,
            "pedro@gmail.com", listOf(Subject.DDI, Subject.AAD, Subject.PMDM)
        ),
        Student(
            1, "Pepe", "12/02/2000", Course.DAM1,
            "pepe@gmail.com", listOf(Subject.AAD, Subject.PMDM)
        ),
        Student(
            1, "Alicia", "05/06/2000", Course.DAM1,
            "alicia@gmail.com", listOf(Subject.SGE, Subject.DDI)
        )
    )


    fun getStudents(): List<Student> = students

    fun getStudentsByCourse(course: Course): List<Student>{
        return students.filter { it.course == course }
    }
}