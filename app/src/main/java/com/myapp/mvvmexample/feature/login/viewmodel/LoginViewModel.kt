package com.myapp.mvvmexample.feature.login.viewmodel

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import android.content.Context
import com.myapp.business.core.callback.Resource
import com.myapp.business.core.callback.SingleLiveEvent
import com.myapp.business.rss.login.usecase.LoginUseCase
import com.myapp.mvvmexample.core.sharereference.ShareReferenceHelper
import com.myapp.mvvmexample.core.sharereference.ShareReferenceHelper.Companion.SHARE_REFERENCE_PASSWORD_KEY
import com.myapp.mvvmexample.core.sharereference.ShareReferenceHelper.Companion.SHARE_REFERENCE_USERNAME_KEY
import com.myapp.mvvmexample.core.util.EncryptUtil
import com.myapp.mvvmexample.feature.login.uimodel.UserUiModel
import javax.inject.Inject


open class LoginViewModel @Inject constructor(var loginUseCase: LoginUseCase) : ViewModel() {

    var saveUserLive = SingleLiveEvent<Resource<String>>()
    var loadUserInfoResult = SingleLiveEvent<Resource<UserUiModel>>()

    fun login(username: String, pass: String): LiveData<Resource<String>> {
        return loginUseCase.buildUseCase(username, pass).execute()
    }

    open fun saveUserInfo(context: Context, username: String, password: String) {
        val sharedPreferences = ShareReferenceHelper(context)
        sharedPreferences.putString(SHARE_REFERENCE_USERNAME_KEY, username)
        sharedPreferences.putString(SHARE_REFERENCE_PASSWORD_KEY, EncryptUtil.encryptPassword(password))
        saveUserLive.postValue(Resource.success("success"))
    }

    open fun loadData(context: Context) {
        val sharedPreferences = ShareReferenceHelper(context)
        var userInfo = UserUiModel()
        userInfo.username = sharedPreferences.getString(SHARE_REFERENCE_USERNAME_KEY)
        userInfo.password = EncryptUtil.descryptPassword(sharedPreferences.getString(SHARE_REFERENCE_PASSWORD_KEY))
        loadUserInfoResult.postValue(Resource.success(userInfo))
    }

}