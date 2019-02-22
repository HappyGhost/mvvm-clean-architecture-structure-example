package com.myapp.business.core.usecase

import com.myapp.business.core.callback.ICallBack
import com.myapp.business.core.exception.BaseException
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

abstract class BaseUseCase<T : ICallBack<K>, K> : IUseCase<T> {

    internal var mDisposable: Disposable? = null

    protected abstract val observable: Observable<K>

    override fun executeByCallBack(callBack: T) {
        mDisposable = observable
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe({ info -> onSuccess(callBack, info) }, { throwable -> onError(callBack, throwable) })
    }

    override fun destroy() {
        if (mDisposable != null) {
            mDisposable!!.dispose()
        }
    }

    protected fun onError(callBack: T, e: Throwable) {
        if (e is BaseException) {
            if (!handleErrorException(e, callBack)) {
                handleGenericError(e, callBack)
            }
        } else {
            callBack.onError(e)
        }
    }

    private fun handleGenericError(e: BaseException, callBack: T) {
        if (e.exceptionType === BaseException.ExceptionType.NETWORK_ERROR) {
            callBack.onNetworkError()
        } else {
            callBack.onGenericError(e)
        }
    }

    protected abstract fun handleErrorException(e: BaseException, callBack: T): Boolean

    protected fun onSuccess(callBack: T, info: K) {
        callBack.onSuccess(info)
    }
}
