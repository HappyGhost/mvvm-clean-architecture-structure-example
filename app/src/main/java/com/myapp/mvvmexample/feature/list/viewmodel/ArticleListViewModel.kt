package com.myapp.mvvmexample.feature.list.viewmodel

import android.arch.lifecycle.ViewModel
import com.myapp.business.rss.article.usecase.GetArticleListUseCase
import javax.inject.Inject


open class ArticleListViewModel @Inject constructor(var getArticleUseCase: GetArticleListUseCase) : ViewModel() {

    fun articleListLiveData() = getArticleUseCase.articleListResult()

    fun refresh() {
        getArticleList()
    }

    fun getArticleList() {
        getArticleUseCase.buildUseCase().execute()
    }
}