package com.myapp.business.rss.login.usecase

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import com.myapp.business.core.callback.Resource
import com.myapp.business.core.callback.SingleLiveEvent
import com.myapp.business.core.usecase.BaseUseCase
import com.myapp.business.core.usecase.IUseCase
import com.myapp.business.rss.login.repository.LoginRepository


interface LoginUseCase : IUseCase<String> {
    fun buildUseCase(username: String, password: String): LoginUseCase
}

class LoginUseCaseImpl(var repository: LoginRepository) : BaseUseCase<String>(), LoginUseCase {

    private var result = SingleLiveEvent<Resource<String>>()

    override fun onError(e: Throwable) {
        result.postValue(Resource.error("error", e.message))
    }

    override fun execute(): LiveData<Resource<String>> {
        result.postValue(Resource.loading(null))
        return super.execute()
    }

    override fun resultLiveData(): MutableLiveData<Resource<String>> = result

    override fun buildUseCase(username: String, password: String): LoginUseCase {
        observable = repository.login(username, password)
        return this
    }
}