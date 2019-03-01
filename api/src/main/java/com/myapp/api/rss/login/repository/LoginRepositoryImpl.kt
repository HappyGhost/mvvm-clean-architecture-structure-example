package com.myapp.api.rss.login.repository

import com.myapp.business.rss.login.repository.LoginRepository
import io.reactivex.Observable
import java.util.concurrent.TimeUnit


class LoginRepositoryImpl : LoginRepository {

    override fun login(username: String, pass: String): Observable<String> {
        return if ("GoBear" == username && "GoBearDemo" == pass) {
            Observable.just("Success").delay(5, TimeUnit.SECONDS)
        } else {
            val error: Observable<String> = Observable.error(Throwable("username or pass is incorrect"))
            error.delaySubscription(5, TimeUnit.SECONDS)
        }
    }

}