package com.tesla.resource_category.exception

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity

class ErrorResponseEntity(body: ErrorResponse, status: HttpStatus) : ResponseEntity<ErrorResponse>(body, status) {
    companion object {
        fun unauthorizedError(code: String?, message: String?) = ErrorResponseEntity(ErrorResponse(code, message),
                HttpStatus.UNAUTHORIZED)

        fun badRequestError(code: String?, message: String?) = ErrorResponseEntity(ErrorResponse(code, message),
                HttpStatus.BAD_REQUEST)

        fun notFoundError(code: String?, message: String?) = ErrorResponseEntity(ErrorResponse(code, message),
                HttpStatus.NOT_FOUND)

        fun serverError(code: String?, message: String?) = ErrorResponseEntity(ErrorResponse(code, message),
                HttpStatus.INTERNAL_SERVER_ERROR)

        fun authTokenError(code: String?, message: String?) = ErrorResponseEntity(ErrorResponse(code, message),
                HttpStatus.INTERNAL_SERVER_ERROR)
    }
}

data class ErrorResponse(
        val code: String?,
        val message: String?,
        val status: String = "ERROR"
)