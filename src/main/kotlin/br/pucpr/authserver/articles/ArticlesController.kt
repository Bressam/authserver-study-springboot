package br.pucpr.authserver.articles

import br.pucpr.authserver.articles.requests.ArticleRequest
import br.pucpr.authserver.articles.responses.ArticleResponse
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.Parameter
import io.swagger.v3.oas.annotations.security.SecurityRequirement
import jakarta.transaction.Transactional
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.*
import java.time.LocalDate
import java.time.ZoneOffset
import java.time.format.DateTimeFormatter

@RestController
@RequestMapping("/articles")
class ArticlesController(private val service: ArticlesService) {
    @Operation(
        summary = "List all articles",
        parameters = [
            Parameter(
                name = "fromDate",
                description = "Date (yyyy-mm-dd) used as filter (optional)",
                example = "2023-02-24"
            )]
    )
    @GetMapping
    fun listArticles(@RequestParam("fromDate") dateString: String?): List<ArticleResponse> {
        if (dateString == null) { return service.findAll().map { it.toResponse() }}

        val date =  LocalDate.parse(dateString, DateTimeFormatter.ISO_DATE) ?: null
        return service.findAll(date = date).map { it.toResponse() }
    }

    @GetMapping("/today")
    @PreAuthorize("permitAll()")
    @SecurityRequirement(name = "AuthServer")
    fun listTodayArticles(): List<ArticleResponse> {
        return service.findAll(date = LocalDate.now(ZoneOffset.UTC)).map { it.toResponse() }
    }

    @Transactional
    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    fun createArticle(@Valid @RequestBody req: ArticleRequest): ResponseEntity<ArticleResponse> {
        return service.save(req)
            .toResponse()
            .let { ResponseEntity.status(HttpStatus.CREATED).body(it) }
    }


    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    @SecurityRequirement(name = "AuthServer")
    fun delete(@PathVariable("id") id: Long): ResponseEntity<Void> =
        if (service.delete(id)) ResponseEntity.ok().build()
        else ResponseEntity.notFound().build()
}