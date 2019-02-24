package com.myapp.business.core.exception

import com.myapp.business.core.constants.ApiCode
import okhttp3.Headers

class BaseApiException : RuntimeException() {
    var code = ApiCode.UNKNOWN
    var httpCode: Int = 0
    var headers: Headers? = null
    var exceptionType: ExceptionType? = null

    enum class ExceptionType {
        HTTP_ERROR,
        NETWORK_ERROR
    }
}
