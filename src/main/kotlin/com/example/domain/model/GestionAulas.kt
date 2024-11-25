package com.example.domain.model

object GestionAulas {

    private val aulas = mutableListOf(
        Aula(
            1, "Aula 101", listOf(Course.DAM1, Course.DAM2), 1, "A"
        ), Aula(
            2, "Laboratorio Informático", listOf(Course.DAW1, Course.DAW2), 2, "B"
        ), Aula(
            3, "Aula de Redes", listOf(Course.ASIR1, Course.ASIR2), 1, "C"
        ), Aula(
            4, "Aula de Tecnología", listOf(Course.SMR1), 3, "A"
        ), Aula(
            5, "Sala de Proyectos", listOf(Course.DAM2, Course.DAW2, Course.SMR2), 2, "D"
        ), Aula(
            6, "Aula Polivalente", listOf(Course.DAM1, Course.SMR1, Course.ASIR1), 0, "E"
        )
    )

    fun getAulas(): List<Aula> = aulas

    fun getAulaPorId(id: Int) = aulas.filter {
        it.id == id
    }

    fun getPabellon(pabellon: String) = aulas.filter {
        it.pabellon.equals(pabellon, ignoreCase = true)
    }

    fun getAulaPorDenominacion(denominacion: String) = aulas.find {
        it.denominacion.equals(denominacion, ignoreCase = true)
    }

    fun nuevaClase(aula: Aula): Aula {
        var aulaFinal = aula
        if (aula.id != null && aula.id > 0) {
            val aulaTemporal = getAulaPorId(aula.id)
            if (aulaTemporal != null) {
                throw IllegalStateException("¡ El aula ya existe !")
            } else {
                aulas.add(aula)
            }
        } else {
            val maxId = aulas.maxWith(Comparator.comparingInt { it.id }).id
            aulaFinal.id = maxId + 1
        }
        return aulaFinal
    }

    fun deleteAula(id: Int): Boolean {
        return aulas.removeIf { it.id == id }
    }
}