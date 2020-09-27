package com.tesla.resource_category.exception

import com.tesla.resource_category.exception.custom_exceptions.*
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class ExceptionHandler {
    @ExceptionHandler(BadRequestException::class)
    fun badRequestException(exception: BadRequestException) =
            ErrorResponseEntity.badRequestError(exception.code, exception.message)

    @ExceptionHandler(NotFoundException::class)
    fun notFoundException(exception: NotFoundException) =
            ErrorResponseEntity.notFoundError(exception.code, exception.message)

    @ExceptionHandler(DatabaseGenericException::class)
    fun databaseException(exception: DatabaseGenericException) =
            ErrorResponseEntity.serverError(exception.code, exception.message)

    @ExceptionHandler(UnAuthorizedException::class)
    fun unauthorizedException(exception: UnAuthorizedException) =
            ErrorResponseEntity.unauthorizedError(exception.code, exception.message)

    @ExceptionHandler(AuthTokenException::class)
    fun unauthorizedException(exception: AuthTokenException) =
            ErrorResponseEntity.authTokenError(exception.code, exception.message)
}