package com.myapp.mvvmexample.feature.list.viewmodel.share

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.myapp.business.rss.article.info.ArticleInfo


open class ArticleShareViewModel : ViewModel() {

    val articleLiveData = MutableLiveData<ArticleInfo>()

    fun selectArticleInfo(articleInfo: ArticleInfo) {
        articleLiveData.value = articleInfo
    }
}