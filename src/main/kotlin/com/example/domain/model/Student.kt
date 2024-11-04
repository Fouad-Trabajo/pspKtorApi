package com.example.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class Student(
    val id: Int,
    val name: String,
    val dateBorn: String,
    val course: Course,
    val email: String,
    val subjects: List<Subject>
)


enum class Course {
    DAM1, DAM2 ,DAW1, DAW2, ASIR1, ASIR2, SMR1, SMR2
}

enum class Subject{
    EIE, PSP, AAD, PMDM, DDI, SGE
}
