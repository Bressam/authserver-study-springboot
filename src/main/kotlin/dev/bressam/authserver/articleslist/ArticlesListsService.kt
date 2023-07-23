package dev.bressam.authserver.articleslist

import dev.bressam.authserver.articleslist.model.ArticlesList
import dev.bressam.authserver.articleslist.requests.ArticlesListRequest
import org.slf4j.LoggerFactory
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class ArticlesListsService(private val repository: ArticlesListsRepository) {

    fun save(req: ArticlesListRequest): ArticlesList {
        val articlesList = ArticlesList(
            name = req.name!!,
            articles = req.articles!!.toMutableSet()
        )

        return repository.save(articlesList)
    }

    fun updateArticlesList(id: Long, req: ArticlesListRequest): ArticlesList? {
        val articlesList = findById(id) ?: return null

        articlesList.updateFrom(req)

        return repository.save(articlesList)
    }

    fun findAll(): List<ArticlesList> = repository.findAll()

    fun findById(id: Long): ArticlesList? = repository.findByIdOrNull(id)

    fun findByName(name: String): ArticlesList? = repository.findByName(name)

    fun delete(id: Long): Boolean {
        val articlesList = repository.findByIdOrNull(id) ?: return false
        log.warn("Articles list deleted. id={} name={}", articlesList.id, articlesList.name)
        repository.delete(articlesList)
        return true
    }

    companion object {
        val log = LoggerFactory.getLogger(ArticlesListsService::class.java)
    }
}