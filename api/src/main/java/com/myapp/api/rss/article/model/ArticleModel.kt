package com.myapp.api.rss.article.model

import org.simpleframework.xml.Element
import org.simpleframework.xml.Namespace
import org.simpleframework.xml.Root


@Root(name = "item")
data class ArticleModel constructor(
    @field:Element(name = "title", required = true)
    @param:Element(name = "title", required = true) var title: String?,
    @field:Element(name = "description", required = true)
    @param:Element(name = "description", required = true)
    var description: String?,
    @field:Element(name = "link", required = true)
    @param:Element(name = "link", required = true)
    var link: String?,
    @field:Element(name = "pubDate", required = true)
    @param:Element(name = "pubDate", required = true) var date: String?,
    @field:Element(name = "thumbnail")
    @param:Element(name = "thumbnail", required = true)
    @Namespace(reference = "http://search.yahoo.com/mrss/", prefix = "media")
    var thumbnail: ArticleThumbnail
)