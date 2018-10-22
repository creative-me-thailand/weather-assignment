package com.chanothai.assignment.domain.error

class NotFoundException(override val message: String): Error(message)
class BadRequestException(override val message: String): Error(message)
class InternalException(override val message: String): Error(message)

object Errors {
    var AvatarNotFound = NotFoundException("Avatar does not found")
    var LocationNotFound = NotFoundException("Location does not found")
    var InvalidInputLocation = BadRequestException("ID does not bank or empty")
}