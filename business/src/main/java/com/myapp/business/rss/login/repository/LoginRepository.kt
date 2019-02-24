package com.myapp.business.rss.login.repository

import io.reactivex.Observable


interface LoginRepository {

    fun login(username: String, pass: String): Observable<String>
}