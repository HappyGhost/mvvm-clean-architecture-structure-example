package com.myapp.api.rss.article.mapper

import com.myapp.api.core.mapper.BaseInfoMapper
import com.myapp.api.rss.article.model.ArticleResponseModel
import com.myapp.business.rss.article.info.ArticleInfo


class ArticleResponseMapper : BaseInfoMapper<List<ArticleInfo>, ArticleResponseModel>() {
    override fun transform(value: ArticleResponseModel): List<ArticleInfo> {
        var result = ArrayList<ArticleInfo>()
        for (item in value.channelModel.articleList!!) {
            run {
                result.add(
                    ArticleInfo(
                        item.title, item.description,
                        item.link, item.thumbnail.link, item.date
                    )
                )
            }
        }
        return result
    }

}