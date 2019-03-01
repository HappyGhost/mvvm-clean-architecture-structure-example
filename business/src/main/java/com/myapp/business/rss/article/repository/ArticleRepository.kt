package com.myapp.business.rss.article.repository

import com.myapp.business.rss.article.info.ArticleInfo
import io.reactivex.Observable


interface ArticleRepository {
    fun getArticleList(): Observable<List<ArticleInfo>>
}