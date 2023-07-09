package br.pucpr.authserver.articles

import br.pucpr.authserver.articles.model.Article
import org.springframework.context.ApplicationListener
import org.springframework.context.event.ContextRefreshedEvent
import org.springframework.stereotype.Component
import java.time.LocalDate
import java.time.ZoneOffset

@Component
class ArticlesBootstrap(
    val articlesRepository: ArticlesRepository
) : ApplicationListener<ContextRefreshedEvent> {
    override fun onApplicationEvent(event: ContextRefreshedEvent) {
        val articles = generateDummyArticles()
        articles.forEach {
            articlesRepository.save(it)
        }
    }

    fun generateDummyArticles(): List<Article> {
        var articles: MutableList<Article> = mutableListOf()

        val article1 = Article(date = LocalDate.now(ZoneOffset.UTC).minusDays(10),
            title = "Article 1: article title",
            subtitle = "This is article 1 brief explanation",
            content = "Content data of article 1. Article 1 explores the issues regarding the problems without solutions on current days"
        )
        articles.add(article1)

        val article2 = Article(date = LocalDate.now(ZoneOffset.UTC).minusDays(10),
            title = "Article 2: article title",
            subtitle = "This is article 2 brief explanation",
            content = "Content data of article 2. Article 2 explores the issues regarding the problems without solutions on current days"
        )
        articles.add(article2)

        val article3 = Article(date = LocalDate.now(ZoneOffset.UTC).minusDays(2),
            title = "Article 3: article title",
            content = "Content data of article 3. Article 3 explores the issues regarding the problems without solutions on current days"
        )
        articles.add(article3)

        val article4 = Article(date = LocalDate.now(ZoneOffset.UTC),
            title = "Article 4: article title",
            subtitle = "This is article 4 brief explanation",
            content = "Content data of article 4. Article 4 explores the issues regarding the problems without solutions on current days"
        )
        articles.add(article4)

        val article5 = Article(date = LocalDate.now(ZoneOffset.UTC),
            title = "Article 5: article title",
            content = "Content data of article 5. Article 5 explores the issues regarding the problems without solutions on current days"
        )
        articles.add(article5)

        return articles.toList()
    }
}