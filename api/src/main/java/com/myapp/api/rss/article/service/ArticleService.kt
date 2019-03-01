package com.myapp.api.rss.article.service

import com.myapp.api.rss.article.model.ArticleResponseModel
import io.reactivex.Observable
import retrofit2.http.GET


interface ArticleService {

    @GET("/news/world/asia/rss.xml")
    fun getCurrencyExchange(): Observable<ArticleResponseModel>
}