package dev.bressam.authserver.articleslist.model

import dev.bressam.authserver.articles.model.Article
import dev.bressam.authserver.articleslist.requests.ArticlesListRequest
import dev.bressam.authserver.articleslist.responses.ArticlesListResponse
import jakarta.persistence.*

@Entity
@Table(name = "ArticlesList")
class ArticlesList(
    @Id @GeneratedValue
    var id: Long? = null,

    @Column(unique = true, nullable = false, length = 60)
    var name: String,

    @ManyToMany
    @JoinTable(
        name = "ArticlesListArticles",
        joinColumns = [JoinColumn(name = "idArticlesList")],
        inverseJoinColumns = [JoinColumn(name = "idArticle")]
    )
    var articles: MutableSet<Article> = mutableSetOf()
) {
    fun toResponse() = ArticlesListResponse(id!!, name, articles)

    fun updateFrom(request: ArticlesListRequest) {
        name = request.name ?: name
        if (request.articles?.isNotEmpty() == true) {
            articles = request.articles?.toMutableSet() ?: mutableSetOf()
        }
    }
}