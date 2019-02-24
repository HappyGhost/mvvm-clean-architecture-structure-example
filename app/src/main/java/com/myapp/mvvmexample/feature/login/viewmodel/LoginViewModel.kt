package com.myapp.mvvmexample.feature.login.viewmodel

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import com.myapp.business.core.callback.Resource
import com.myapp.business.rss.login.usecase.LoginUseCase
import javax.inject.Inject


open class LoginViewModel @Inject constructor(var loginUseCase: LoginUseCase) : ViewModel() {

    fun login(username: String, pass: String): LiveData<Resource<String>> {
        return loginUseCase.buildUseCase(username, pass).execute()
    }
}