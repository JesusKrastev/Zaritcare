package com.zaritcare.utilities.validation

// CompositeValidation.kt -----------------------------------------------
// Es una clase de utilidad que tiene una lista de validaciones que debemos pasar antes
// de dar por válidos los datos de un formulario.
// Rehusamos para ello los objetos que representan los estados de validación de cada campo y si alguno
// de ellos tiene error lo indicaremos en la propiedad calculada de solo lectura hayError de esta clase.
open class CompositeValidation : Validation {
    private val validaciones = mutableListOf<Validation>()
    fun add(validation: Validation): CompositeValidation {
        validaciones.add(validation)
        return this
    }
    override val hasError: Boolean
        get() = validaciones.any { it.hasError }

    override val errorMessage: String?
        get() = validaciones.firstOrNull { it.hasError }?.errorMessage
}