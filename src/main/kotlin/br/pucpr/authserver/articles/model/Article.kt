package br.pucpr.authserver.articles.model

import br.pucpr.authserver.articles.responses.ArticleResponse
import br.pucpr.authserver.articleslist.model.ArticlesList
import com.fasterxml.jackson.annotation.JsonIgnore
import jakarta.persistence.*
import org.springframework.format.annotation.DateTimeFormat
import java.time.LocalDate

@Entity
class Article(
    @Id @GeneratedValue
    var id: Long? = null,

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(nullable = false)
    var date: LocalDate,

    @Column(nullable = false)
    var title: String,

    @Column(length = 240)
    var subtitle: String? = null,

    @Column(nullable = false)
    var content: String,

    @JsonIgnore
    @ManyToMany(mappedBy = "articles")
    val articlesList: MutableSet<ArticlesList> = mutableSetOf()
) {
    fun toResponse() = ArticleResponse(id!!, date, title, subtitle ?: "", content)
}