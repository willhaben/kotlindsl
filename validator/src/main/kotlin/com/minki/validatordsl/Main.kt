package com.minki.validatordsl

import com.markodevcic.kvalidation.ValidationResult
import com.minki.validatordsl.domain.User

fun main() {
    val user1 = User("John", "Doe")
    val result1 = UserValidator(user1).validate()

    printValidatorResult(user1, result1)

    val user2 = User("Definitive a too long first name", "Doe")
    val result2 = UserValidator(user2).validate()

    printValidatorResult(user2, result2)
}

private fun printValidatorResult(user : User, result : ValidationResult) {
    if(result.isValid) {
        println("$user is valid!")
    } else {
        val errorText = result.validationErrors.joinToString("\n") {
            "Error code is: ${it.errorCode} Error message is: ${it.message}"
        }
        println("$user is invalid!\n$errorText")
    }
}