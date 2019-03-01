package com.myapp.api.rss.article.model

import org.simpleframework.xml.ElementList
import org.simpleframework.xml.Root


@Root(name = "channel", strict = false)
data class ChannelModel @JvmOverloads constructor(
    @field:ElementList(name = "item", inline = true, required = false)
    @param:ElementList(name = "item", inline = true, required = false)
    var articleList: List<ArticleModel>? = ArrayList()
)