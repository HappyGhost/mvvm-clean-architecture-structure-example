package com.myapp.api.rss.article.repository

import com.myapp.api.core.repository.BaseApiRepository
import com.myapp.api.rss.article.mapper.ArticleResponseMapper
import com.myapp.api.rss.article.model.ArticleResponseModel
import com.myapp.api.rss.article.service.ArticleService
import com.myapp.business.rss.article.info.ArticleInfo
import com.myapp.business.rss.article.repository.ArticleRepository
import io.reactivex.Observable


class ArticleRepositoryImpl(var service: ArticleService) : BaseApiRepository(), ArticleRepository {

    override fun getArticleList(): Observable<List<ArticleInfo>> {
        return processRequest(service.getCurrencyExchange().map { t: ArticleResponseModel ->
            ArticleResponseMapper().transform(t)
        })
    }

}