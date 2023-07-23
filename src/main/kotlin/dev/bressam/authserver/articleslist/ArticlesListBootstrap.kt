package dev.bressam.authserver.articleslist

import dev.bressam.authserver.articles.ArticlesBootstrap
import dev.bressam.authserver.articles.ArticlesRepository
import dev.bressam.authserver.articles.model.Article
import dev.bressam.authserver.articleslist.model.ArticlesList
import org.springframework.context.ApplicationListener
import org.springframework.context.event.ContextRefreshedEvent
import org.springframework.stereotype.Component

@Component
class ArticlesListBootstrap(
    val articlesListRepository: ArticlesListsRepository,
    val articlesRepository: ArticlesRepository,
    val articlesBootstrap: ArticlesBootstrap
) : ApplicationListener<ContextRefreshedEvent> {
    override fun onApplicationEvent(event: ContextRefreshedEvent) {
        articlesBootstrap.populateDummyArticles()

        generateAndSaveDefaultReadingLists()
    }

    fun generateAndSaveDefaultReadingLists() {
        var favoriteArticlesList = ArticlesList(name = "Favorites", articles = mutableSetOf())
        val readLaterArticlesList = ArticlesList(name = "Read later", articles = mutableSetOf())
        articlesListRepository.save(favoriteArticlesList)
        articlesListRepository.save(readLaterArticlesList)

        // Populate favorites with random list
        favoriteArticlesList.articles = generateRandomFavoriteList().toMutableSet()
        articlesListRepository.save(favoriteArticlesList)
    }

    fun generateRandomFavoriteList(): List<Article> {
        var randomFavoriteArticles: MutableList<Article> = mutableListOf()
        val currentArticles = articlesRepository.findAll()

        for (i in 1..3) {
            randomFavoriteArticles.add(currentArticles.random())
        }

        return randomFavoriteArticles.toList()
    }
}