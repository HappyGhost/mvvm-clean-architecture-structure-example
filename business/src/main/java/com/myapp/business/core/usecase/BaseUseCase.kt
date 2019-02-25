package com.myapp.business.core.usecase

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import com.myapp.business.core.callback.Resource
import com.myapp.business.core.callback.Status
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
        resultLiveData().postValue(Resource.loading(null))
        return resultLiveData()
    }

    override fun destroy() {
        if (mDisposable != null) {
            mDisposable!!.dispose()
        }
    }

    abstract fun onError(e: Throwable)

    open fun onSuccess(info: K) {
        resultLiveData().postValue(Resource(Status.SUCCESS, info, "success"))
    }

    abstract fun resultLiveData(): MutableLiveData<Resource<K>>
}
