package com.myapp.business.rss.login.usecase

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.myapp.business.core.callback.Resource
import com.myapp.business.core.usecase.BaseUseCase
import com.myapp.business.core.usecase.IUseCase
import com.myapp.business.rss.login.repository.LoginRepository


interface LoginUseCase : IUseCase<String> {
    fun buildUseCase(username: String, password: String): LoginUseCase
}

class LoginUseCaseImpl(var repository: LoginRepository) : BaseUseCase<String>(), LoginUseCase {

    private var result = MutableLiveData<Resource<String>>()

    override fun onError(e: Throwable) {
        result.postValue(Resource.error("Login Error", e.message))
    }

    override fun onSuccess(info: String) {
        result.postValue(Resource.success(info))
    }

    override fun resultLiveData(): LiveData<Resource<String>> = result

    override fun buildUseCase(username: String, password: String): LoginUseCase {
        observable = repository.login(username, password)
        return this
    }
}