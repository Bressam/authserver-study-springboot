package br.pucpr.authserver.articles.responses

import jakarta.persistence.Column

data class ArticleResponse(
    val id: Long,
    var title: String,
    var subtitle: String = "",
    var content: String
)