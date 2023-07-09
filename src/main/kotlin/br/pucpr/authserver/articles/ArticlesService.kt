package br.pucpr.authserver.articles

import br.pucpr.authserver.articles.model.Article
import br.pucpr.authserver.articles.requests.ArticleRequest
import org.springframework.data.domain.Sort
import org.springframework.stereotype.Service
import java.time.LocalDate
import java.time.ZoneOffset

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

    fun findAll(date: LocalDate? = null): List<Article> =
        if (date == null) repository.findAll(Sort.by("date"))
        else repository.findAllByDate(date = date)
}