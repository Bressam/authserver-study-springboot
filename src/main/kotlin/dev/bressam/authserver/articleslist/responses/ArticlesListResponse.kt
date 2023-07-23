package dev.bressam.authserver.articleslist.responses

import dev.bressam.authserver.articles.model.Article

data class ArticlesListResponse(
    var id: Long,
    var name: String,
    val articles: Set<Article>
)