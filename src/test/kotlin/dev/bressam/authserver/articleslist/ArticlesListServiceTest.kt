package dev.bressam.authserver.articleslist

import dev.bressam.authserver.Stubs
import dev.bressam.authserver.articles.model.Article
import dev.bressam.authserver.articleslist.requests.ArticlesListRequest
import io.kotest.matchers.shouldBe
import io.mockk.every
import io.mockk.justRun
import io.mockk.mockk
import org.junit.jupiter.api.Test
import org.springframework.data.repository.findByIdOrNull

class ArticlesListServiceTest {
    private val articlesListRepositoryMock = mockk<ArticlesListsRepository>()
    private val service = ArticlesListsService(articlesListRepositoryMock)

    @Test
    fun `Delete should return false for id not found`() {
        every { articlesListRepositoryMock.findByIdOrNull(100) } returns null
        service.delete(100) shouldBe false
    }

    @Test
    fun `Delete must return true if the user is deleted`() {
        val articlesList = Stubs.articlesListStub()
        every { articlesListRepositoryMock.findByIdOrNull(1) } returns articlesList
        justRun { articlesListRepositoryMock.delete(articlesList) }
        service.delete(1) shouldBe true
    }

    @Test
    fun `Update article list articles should return updated entity`() {
        val originalArticles: List<Article> = listOf(Stubs.articleStub(1), Stubs.articleStub(2))
        val originalArticlesList = Stubs.articlesListStub(1, articles = originalArticles)
        val newArticles: List<Article> = listOf(Stubs.articleStub(2), Stubs.articleStub(3))
        val updatedArticlesListRequest = ArticlesListRequest(null, newArticles.toSet())

        every { articlesListRepositoryMock.findByIdOrNull(1) } returns originalArticlesList
        every { articlesListRepositoryMock.save(any()) } returnsArgument 0
        service.updateArticlesList(originalArticlesList.id!!, updatedArticlesListRequest)!!.articles shouldBe newArticles
    }

    @Test
    fun `Update article list articles should return null for invalid id`() {
        val articles: List<Article> = listOf(Stubs.articleStub(2), Stubs.articleStub(3))
        val updatedArticlesListRequest = ArticlesListRequest(null, articles.toSet())

        every { articlesListRepositoryMock.findByIdOrNull(1) } returns null
        every { articlesListRepositoryMock.save(any()) } returnsArgument 0
        service.updateArticlesList(1, updatedArticlesListRequest) shouldBe null
    }
}