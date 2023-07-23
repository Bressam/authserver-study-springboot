package dev.bressam.authserver.articleslist.requests

import dev.bressam.authserver.articles.model.Article
import jakarta.validation.constraints.NotBlank

data class ArticlesListRequest(
    @field:NotBlank
    var name: String?,

    var articles: Set<Article> = setOf()
)