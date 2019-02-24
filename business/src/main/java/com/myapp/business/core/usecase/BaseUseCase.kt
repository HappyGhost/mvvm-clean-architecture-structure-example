package com.myapp.business.core.usecase

import androidx.lifecycle.LiveData
import com.myapp.business.core.callback.Resource
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

interface IUseCase<T> {
    fun execute(): LiveData<Resource<T>>
    fun destroy()
}

abstract class BaseUseCase<K> : IUseCase<K> {

    internal var mDisposable: Disposable? = null

    protected var observable: Observable<K>? = null


    override fun execute(): LiveData<Resource<K>> {
        mDisposable = observable
            ?.observeOn(AndroidSchedulers.mainThread())
            ?.subscribeOn(Schedulers.io())
            ?.subscribe({ info -> onSuccess(info) }, { throwable -> onError(throwable) })
        return resultLiveData()
    }

    override fun destroy() {
        if (mDisposable != null) {
            mDisposable!!.dispose()
        }
    }

    abstract fun onError(e: Throwable)

    abstract fun onSuccess(info: K)

    abstract fun resultLiveData(): LiveData<Resource<K>>
}
