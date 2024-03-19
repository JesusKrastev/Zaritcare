package com.kinoyamboladmin.utilities.validation.validators

import com.zaritcare.utilities.validation.Validation
import com.zaritcare.utilities.validation.Validator

class EmailValidator(
    val error: String = "Correo no válido"
) : Validator<String> {
    override fun validate(texto: String): Validation {
        return object : Validation {
            override val hasError: Boolean
                get() = !Regex("^[A-Za-z](.*)([@]{1})(.{1,})(\\.)(.{1,})$").matches(texto)
            override val errorMessage: String
                get() = error
        }
    }
}