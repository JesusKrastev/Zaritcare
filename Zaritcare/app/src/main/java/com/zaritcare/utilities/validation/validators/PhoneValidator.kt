package com.zaritcare.utilities.validation.validators

import com.zaritcare.utilities.validation.Validation
import com.zaritcare.utilities.validation.Validator

class PhoneValidator(
    val error: String = "Teléfono no válido"
) : Validator<String> {
    override fun validate(text: String): Validation {
        return object : Validation {
            override val hasError: Boolean
                get() = !Regex("^[0-9 ]{9,18}$").matches(text)
            override val errorMessage: String
                get() = this@PhoneValidator.error
        }
    }
}