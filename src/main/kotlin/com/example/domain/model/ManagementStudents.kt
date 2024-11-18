package com.example.domain.model

object ManagementStudents {


    private val students = mutableListOf(
        Student(
            1,
            "Pedro",
            "05/06/2000",
            Course.DAM1,
            "pedro@gmail.com",
            listOf(Subject.DDI, Subject.AAD, Subject.PMDM)
        ), Student(
            2,
            "Pepe",
            "12/02/2000",
            Course.DAM1,
            "pepe@gmail.com",
            listOf(Subject.AAD, Subject.PMDM)
        ), Student(
            3,
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


    // find devuelve un elemento, porque solo puede haber un alumno con ese id en concreto (PK)
    private fun getStudentById(id: Int) = students.find {
        it.id == id
    }


    /**
     * Se comprueba si el id viene nulo o si nos lo pasan.
     * Si nos lo pasan se comprueba que no exista ya un alumno con ese id.
     * Si llega nulo se calcula con MAX + 1
     */
    fun newStudent(student: Student): Student {
        var studentFinal = student
        if (student.id != null && student.id > 0) {
            val studentTmp = getStudentById(student.id)
            if (studentTmp != null) {
                // Alumno existente, valor repetido
                throw IllegalStateException("¡ El alumno ya existe !")
            } else {
                // Se inserta el nuevo alumno
                students.add(student)
            }
        } else {
            // se obtiene el valor máximo de los idAlumno y se suma 1 para insertarlo como nuevo id
            val maxId = students.maxWith(Comparator.comparingInt { it.id }).id
            studentFinal.id = maxId + 1
            students.add(student)
        }
        return studentFinal
    }

    fun deleteStudent(id: Int): Boolean {
        return students.removeIf { it.id == id }
    }
}