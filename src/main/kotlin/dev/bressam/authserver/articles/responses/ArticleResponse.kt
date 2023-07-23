package dev.bressam.authserver.articles.responses

import java.time.LocalDate

data class ArticleResponse(
    val id: Long,
    var date: LocalDate,
    var title: String,
    var subtitle: String = "",
    var content: String
)