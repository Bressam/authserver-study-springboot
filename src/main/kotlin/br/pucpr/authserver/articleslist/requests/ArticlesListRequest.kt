package br.pucpr.authserver.articleslist.requests

import br.pucpr.authserver.articles.model.Article
import jakarta.validation.constraints.NotBlank

data class ArticlesListRequest(
    @field:NotBlank
    var name: String?,

    var articles: Set<Article> = setOf()
)