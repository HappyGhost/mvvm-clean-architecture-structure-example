package com.myapp.api.rss.article.model

import org.simpleframework.xml.Attribute
import org.simpleframework.xml.Root

@Root
data class ArticleThumbnail(
    @field:Attribute(name = "url", required = false)
    @param:Attribute(name = "url", required = false)
    var link: String? = null
)