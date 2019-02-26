package com.myapp.mvvmexample.feature.login.uimodel


class UserUiModel {
    var username: String = ""
    var password: String = ""
    val isRemember
        get() = !username.isEmpty()
}