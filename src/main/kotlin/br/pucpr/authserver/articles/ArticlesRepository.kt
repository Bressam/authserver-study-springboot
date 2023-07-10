package br.pucpr.authserver.articles

import br.pucpr.authserver.articles.model.Article
import org.springframework.data.domain.Sort
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.time.LocalDate

@Repository
interface ArticlesRepository: JpaRepository<Article, Long> {

    fun findAllByDate(date: LocalDate, sort: Sort): List<Article>

    fun findAllByDate(date: LocalDate): List<Article>
}