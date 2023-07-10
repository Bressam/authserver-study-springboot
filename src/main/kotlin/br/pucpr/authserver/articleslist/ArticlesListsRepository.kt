package br.pucpr.authserver.articleslist

import br.pucpr.authserver.articleslist.model.ArticlesList
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ArticlesListsRepository: JpaRepository<ArticlesList, Long> {
    fun findByName(name: String): ArticlesList?
}