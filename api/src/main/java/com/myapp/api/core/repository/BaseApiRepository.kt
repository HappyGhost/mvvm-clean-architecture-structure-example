package com.myapp.api.core.repository

import com.myapp.api.core.mapper.ExceptionMapper
import com.myapp.business.core.exception.BaseApiException
import io.reactivex.Observable
import io.reactivex.ObservableEmitter
import io.reactivex.ObservableSource
import io.reactivex.functions.Function
import retrofit2.adapter.rxjava2.HttpException
import java.io.IOException

open class BaseApiRepository {

    fun <T> processRequest(observableRequest: Observable<T>): Observable<T> {
        return observableRequest.onErrorResumeNext(Function<Throwable, ObservableSource<T>> { throwable ->
            Observable.create { emitter ->
                convertErrorToBaseException(
                    emitter,
                    throwable
                )
            }
        })
    }

    private fun convertErrorToBaseException(emitter: ObservableEmitter<*>, throwable: Throwable) {
        when (throwable) {
            is HttpException -> emitter.onError(ExceptionMapper().transform(throwable))
            is IOException -> {
                val baseException = BaseApiException()
                baseException.exceptionType = BaseApiException.ExceptionType.NETWORK_ERROR
                emitter.onError(baseException)
            }
            else -> emitter.onError(throwable)
        }
    }
}
