package com.myapp.api.rss.article.model

import org.simpleframework.xml.Element
import org.simpleframework.xml.Root

@Root(name = "rss", strict = false)
data class ArticleResponseModel @JvmOverloads constructor(
    @field:Element(name = "channel")
    @param:Element(name = "channel")
    var channelModel: ChannelModel
)