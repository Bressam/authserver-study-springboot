package br.pucpr.authserver.articles

import br.pucpr.authserver.Stubs
import br.pucpr.authserver.articles.model.Article
import io.kotest.matchers.shouldBe
import io.mockk.every
import io.mockk.justRun
import io.mockk.mockk
import org.junit.jupiter.api.Test
import org.springframework.data.repository.findByIdOrNull
import java.time.LocalDate

class ArticlesServiceTest {
    private val articleRepositoryMock = mockk<ArticlesRepository>()
    private val service = ArticlesService(articleRepositoryMock)

    @Test
    fun `Delete should return false for id not found`() {
        every { articleRepositoryMock.findByIdOrNull(100) } returns null
        service.delete(100) shouldBe false
    }

    @Test
    fun `Delete must return true if the user is deleted`() {
        val article = Stubs.articleStub()
        every { articleRepositoryMock.findByIdOrNull(1) } returns article
        justRun { articleRepositoryMock.delete(article) }
        service.delete(1) shouldBe true
    }

    @Test
    fun `Find by current date should return current date articles`() {
        val todayArticle = Stubs.articleStub(id = 1, date = LocalDate.now())
        val articles: MutableList<Article> = mutableListOf()
        articles.add(todayArticle)

        every { articleRepositoryMock.findAllByDate(LocalDate.now()) } returns articles
        justRun { articleRepositoryMock.saveAll(articles) }

        service.findAll(LocalDate.now().toString()).count() shouldBe articles.count()
    }
}