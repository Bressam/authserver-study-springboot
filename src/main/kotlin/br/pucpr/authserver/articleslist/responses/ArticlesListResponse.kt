package br.pucpr.authserver.articleslist.responses

import br.pucpr.authserver.articles.model.Article

data class ArticlesListResponse(
    var id: Long,
    var name: String,
    val articles: Set<Article>
)