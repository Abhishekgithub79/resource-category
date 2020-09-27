package com.tesla.resource_category.exception.custom_exceptions

import com.tesla.resource_category.exception.ErrorMessage

class DatabaseGenericException(e: ErrorMessage, param: String? = null): BaseException(e.name, e.errorMessage.format(param)) {

}