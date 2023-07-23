package dev.bressam.authserver.articles

import dev.bressam.authserver.articles.model.Article
import dev.bressam.authserver.articles.requests.ArticleRequest
import org.slf4j.LoggerFactory
import org.springframework.data.domain.Sort
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import java.time.LocalDate
import java.time.ZoneOffset
import java.time.format.DateTimeFormatter

@Service
class ArticlesService(private val repository: ArticlesRepository) {

    fun save(req: ArticleRequest): Article {
        val article = Article(
            date = LocalDate.now(ZoneOffset.UTC),
            title = req.title!!,
            subtitle = req.subtitle!!,
            content = req.content!!
        )
        return repository.save(article)
    }

    fun findAll(dateString: String? = null, requestSortBy: String? = null): List<Article> {
        val sortBy = requestSortBy ?: "date"
        if (dateString == null || (LocalDate.parse(dateString, DateTimeFormatter.ISO_DATE) ?: null) == null) {
            return repository.findAll(Sort.by(sortBy))
        }

        val date = LocalDate.parse(dateString, DateTimeFormatter.ISO_DATE) ?: null
        if (date == null) {
            return repository.findAll(Sort.by(sortBy))
        } else {
            return repository.findAllByDate(date, Sort.by(sortBy))
        }
    }


    fun delete(id: Long): Boolean {
        val article = repository.findByIdOrNull(id) ?: return false
        log.warn("Article deleted. id={} title={}", article.id, article.title)
        repository.delete(article)
        return true
    }

    companion object {
        val log = LoggerFactory.getLogger(ArticlesService::class.java)
    }
}