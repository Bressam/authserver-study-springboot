package br.pucpr.authserver.articles.model

import br.pucpr.authserver.articles.responses.ArticleResponse
import jakarta.persistence.*

@Entity
class Article(
    @Id @GeneratedValue
    var id: Long? = null,

    @Column(nullable = false)
    var title: String,

    @Column(length = 240)
    var subtitle: String,

    @Column(nullable = false)
    var content: String
) {
    fun toResponse() = ArticleResponse(title, subtitle, content)
}