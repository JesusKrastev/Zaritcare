package com.zaritcare.utilities.validation.validators

import com.zaritcare.utilities.validation.Validation
import com.zaritcare.utilities.validation.Validator

class ValidatorNonEmptyText(
    val error: String = "El campo no puede estar vacío"
) : Validator<String> {
    override fun validate(text: String): Validation {
        return object : Validation {
            override val hasError: Boolean
                get() = text.isEmpty()
            override val errorMessage: String
                get() = this@ValidatorNonEmptyText.error
        }
    }
}
