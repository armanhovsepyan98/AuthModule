package com.authmodule.common

object Validator {

    fun validateEmail(email: String): ValidationResult {
        val emailPattern = Regex("^\\S+@\\S+\\.\\S+\$")

        return ValidationResult(email.isNotEmpty() && emailPattern.matches(email))
    }

    fun validatePassword(password: String): ValidationResult {
        return ValidationResult(password.isNotEmpty() && password.length>=6)
    }

    fun validateCheckBox(isChecked: Boolean): ValidationResult {
        return ValidationResult(isChecked)
    }
}

data class ValidationResult(
    val status: Boolean = false
)