package com.tesla.resource_category.exception

enum class ErrorMessage (val errorMessage: String) {
    UNAUTHORISED("Access Denied"),
    BAD_REQUEST("Invalid Request: %s"),
    NOT_FOUND("Invalid Request: %s"),
    SERVER_ERROR("Internal Error"),
    DATABASE_ERROR("Database Error"),
    AUTH_TOKEN_ERROR("failed to generate token, please try again")
}