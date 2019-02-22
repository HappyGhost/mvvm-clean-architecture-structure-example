package com.myapp.business.core.callback

import com.myapp.business.core.exception.BaseException

interface ICallBack<T> {

    fun onSuccess(info: T)

    fun onError(e: Throwable)

    fun onNetworkError()

    fun onGenericError(e: BaseException)
}
