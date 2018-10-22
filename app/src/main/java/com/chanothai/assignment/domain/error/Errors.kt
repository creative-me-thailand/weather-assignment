package com.chanothai.assignment.domain.error

import java.lang.RuntimeException

class NotFoundException(override val message: String): RuntimeException(message)
class BadRequestException(override val message: String): RuntimeException(message)
class InternalException(override val message: String): RuntimeException(message)

object Errors {
    var AvatarNotFound = NotFoundException("Avatar does not found")
    var LocationNotFound = NotFoundException("Location does not found")
    var InvalidInputLocation = BadRequestException("ID does not bank or empty")
}