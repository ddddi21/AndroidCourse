package com.example.androidcourse.common.exception

sealed class DomainException(error: String): Exception(error)

object LettersNotAllowedException: DomainException("Something get wrong")

object InsufficientDataException: DomainException("Insufficient data")

object UnExpectedException: DomainException("Unexpected error")

