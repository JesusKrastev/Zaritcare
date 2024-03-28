package com.zaritcare.utilities.validation.validators

import com.zaritcare.utilities.validation.Validation
import com.zaritcare.utilities.validation.Validator

class PasswordValidator(
    val error: String = "Password débil (mínimo 8 caracteres, 1 mayúscula, 1 minúscula, 1 número y 1 carácter especial)"
) : Validator<String> {
    override fun validate(text: String): Validation {
        return object : Validation {

            // ^                 # comienzo de la cadena
            // (?=.*[0-9])       # un dígito debe ocurrir al menos una vez
            // (?=.*[a-z])       # una letra minúscula debe ocurrir al menos una vez
            // (?=.*[A-Z])       # una letra mayúscula debe ocurrir al menos una vez
            // (?=.*[@#$%^&+=_])  # un carácter especial debe ocurrir al menos una vez
            // (?=\S+$)          # no se permite espacios en blanco en toda la cadena
            // .{8,}             # cualquier cosa, al menos ocho lugares
            // $                 # final de la cadena
            override val hasError: Boolean
                get() = !Regex("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=_])(?=\\S+$).{8,}$").matches(text)

            override val errorMessage: String
                get() = this@PasswordValidator.error
        }
    }
}