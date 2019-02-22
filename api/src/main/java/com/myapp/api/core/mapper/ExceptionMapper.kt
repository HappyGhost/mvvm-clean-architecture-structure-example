package com.myapp.api.core.mapper

import com.myapp.business.core.exception.BaseException
import retrofit2.HttpException

class ExceptionMapper : BaseInfoMapper<BaseException, HttpException>() {

    override fun transform(httpException: HttpException): BaseException {
        val baseException = BaseException()
        val response = httpException.response()
        baseException.httpCode = response.code()
        baseException.headers = response.headers()
        baseException.exceptionType = BaseException.ExceptionType.HTTP_ERROR
        //handle common error json from server
        return baseException
    }
}
