package br.pucpr.authserver

import br.pucpr.authserver.articles.model.Article
import br.pucpr.authserver.articleslist.model.ArticlesList
import br.pucpr.authserver.users.Role
import br.pucpr.authserver.users.User
import java.time.LocalDate
import java.time.ZoneOffset
import kotlin.random.Random

fun randomString(
    length: Int = 10, allowedChars: List<Char> =
        ('A'..'Z') + ('a'..'z') + ('0'..'9')
) = (1..length)
    .map { allowedChars.random() }
    .joinToString()

object Stubs {
    fun userStub(
        id: Long? = Random.nextLong(1, 1000),
        roles: List<String> = listOf("USER")
    ): User {
        val name = "user-${id ?: "new"}"
        return User(
            id = id,
            name = name,
            email = "$name@email.com",
            password = randomString(),
            roles = roles
                .mapIndexed { i, it -> Role(i.toLong(), it) }
                .toMutableSet()
        )
    }

    fun articleStub(
        id: Long? = Random.nextLong(1, 1000),
        addSubtitle: Boolean = true,
        date: LocalDate? = null
    ): Article {
        val date =  date ?: LocalDate.now(ZoneOffset.UTC)
        val title = "article $id"
        val subtitle = if (addSubtitle) "subtitle of article $id" else ""
        val content = "content of article $id"
        return Article(id, date, title, subtitle, content, mutableSetOf())
    }

    fun articlesListStub(
        id: Long? = Random.nextLong(1, 1000),
        name: String? = null,
        articles: List<Article> = listOf()
        ): ArticlesList {
        val name = name ?: "articles list $id"

        return ArticlesList(id, name, articles.toMutableSet())
    }
}