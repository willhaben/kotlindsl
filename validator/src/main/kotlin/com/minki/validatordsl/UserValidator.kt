package com.minki.validatordsl

import com.markodevcic.kvalidation.ValidatorBase
import com.markodevcic.kvalidation.onError
import com.markodevcic.kvalidation.rules
import com.minki.validatordsl.domain.User

const val FATAL_USER_ERROR = 0

class UserValidator(user: User) : ValidatorBase<User>(user) {

    init {
        forProperty { p -> p.firstName } rules {
            nonNull()
            mustBe { n -> n?.startsWith("J") ?: false } whenIs {
                it.lastName == "Doe"
            } onError {
                errorCode(FATAL_USER_ERROR)
                errorMessage("If last name is Doe then firstName must start with the 'J' character!")
            }
        }

        forProperty { p -> p.lastName } rules {
            length(3, 10) onError {
                errorCode(1)
                errorMessage("First name must have a character length between 3 and 10!")
            }
        }
    }
}