package com.myapp.mvvmexample.feature.login.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.myapp.business.core.callback.Resource
import com.myapp.business.rss.login.usecase.LoginUseCase


class LoginViewModel constructor(private var loginUseCase: LoginUseCase) : ViewModel() {

    fun login(username: String, pass: String): LiveData<Resource<String>> {
        return loginUseCase.buildUseCase(username, pass).execute()
    }
}