package com.zaritcare.utilities.validation.validators

import android.util.Range
import com.zaritcare.utilities.validation.Validation
import com.zaritcare.utilities.validation.Validator

class RealNumberValidator(
    val range: Range<Double> = Range(0.0, Double.MAX_VALUE),
    val error: String = "Se espera un valor real"
) : Validator<String> {
    override fun validate(text: String): Validation {
        return object : Validation {
            override val hasError: Boolean
                get() = !text.matches(Regex("^[0-9]+(\\.[0-9]+)?$"))
                        ||
                        !range.contains(text.toDouble())
            override val errorMessage: String
                get() = this@RealNumberValidator.error
        }
    }
}