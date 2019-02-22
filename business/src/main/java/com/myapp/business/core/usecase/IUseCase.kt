package com.myapp.business.core.usecase

interface IUseCase<T> {
    fun executeByCallBack(callBack: T)
    fun destroy()
}
