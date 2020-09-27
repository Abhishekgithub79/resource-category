package com.tesla.resource_category.exception.custom_exceptions

open class BaseException: Exception {
    val code: String?

    constructor( code:String?, message: String?): super( message ) {
        this.code = code
    }
}