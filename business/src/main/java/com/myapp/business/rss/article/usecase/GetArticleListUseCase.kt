package com.myapp.business.rss.article.usecase

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import com.myapp.business.core.callback.Resource
import com.myapp.business.core.usecase.BaseUseCase
import com.myapp.business.core.usecase.IUseCase
import com.myapp.business.rss.article.info.ArticleInfo
import com.myapp.business.rss.article.repository.ArticleRepository


interface GetArticleListUseCase : IUseCase<List<ArticleInfo>> {
    fun buildUseCase(): GetArticleListUseCase
    fun articleListResult(): LiveData<Resource<List<ArticleInfo>>>
}

class GetArticleListUseCaseImpl(var repository: ArticleRepository) : BaseUseCase<List<ArticleInfo>>(), GetArticleListUseCase {

    private val articleListResult = MutableLiveData<Resource<List<ArticleInfo>>>()

    override fun onError(e: Throwable) {
        articleListResult.postValue(Resource.error("error", null))
    }

    override fun resultLiveData(): MutableLiveData<Resource<List<ArticleInfo>>> = articleListResult

    override fun buildUseCase(): GetArticleListUseCase {
        observable = repository.getArticleList()
        return this
    }

    override fun articleListResult(): LiveData<Resource<List<ArticleInfo>>> = articleListResult
}