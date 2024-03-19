package com.kinoyamboladmin.utilities.validation.validators

import android.util.Range
import com.zaritcare.utilities.validation.Validation
import com.zaritcare.utilities.validation.Validator

class IntegerNumberValidator(
    val range: Range<Int> = Range(0, Int.MAX_VALUE),
    val error: String = "Se espera un valor entero"
) : Validator<String> {
    override fun validate(texto: String): Validation {
        return object : Validation {
            override val hasError: Boolean
                get() = !texto.matches(Regex("^[+-]?[0-9]+$"))
                        ||
                        !range.contains(texto.toInt())
            override val errorMessage: String
                get() = error
        }
    }
}