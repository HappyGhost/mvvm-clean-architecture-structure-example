package com.myapp.api.rss.repository

import com.myapp.business.rss.login.repository.LoginRepository
import io.reactivex.Observable


class LoginRepositoryImpl : LoginRepository {

    override fun login(username: String, pass: String): Observable<String> {
        return if ("GoBear" == username && "GoBearDemo" == pass) {
            Observable.just("success")
        } else {
            Observable.error(Throwable("username or pass is incorrect"))
        }
    }

}