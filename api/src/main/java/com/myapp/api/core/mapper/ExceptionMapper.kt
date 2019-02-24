package com.myapp.api.core.mapper

import com.myapp.business.core.exception.BaseApiException
import retrofit2.HttpException

class ExceptionMapper : BaseInfoMapper<BaseApiException, HttpException>() {

    override fun transform(httpException: HttpException): BaseApiException {
        val baseException = BaseApiException()
        val response = httpException.response()
        baseException.httpCode = response.code()
        baseException.headers = response.headers()
        baseException.exceptionType = BaseApiException.ExceptionType.HTTP_ERROR
        //handle common error json from server
        return baseException
    }
}
