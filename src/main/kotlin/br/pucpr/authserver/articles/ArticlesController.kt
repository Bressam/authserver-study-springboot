package br.pucpr.authserver.articles

import br.pucpr.authserver.articles.requests.ArticleRequest
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.Parameter
import io.swagger.v3.oas.annotations.security.SecurityRequirement
import jakarta.transaction.Transactional
import jakarta.validation.Valid
import org.springframework.http.ResponseEntity
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/articles")
class ArticlesController() {
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
    fun listArticles(@RequestParam("fromDate") role: String?): ResponseEntity<String> {
        return ResponseEntity.ok("list")
    }

    @Transactional
    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    fun createArticle(@Valid @RequestBody req: ArticleRequest): ResponseEntity<ArticleRequest> {
        return ResponseEntity.ok(req)
    }


    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    @SecurityRequirement(name = "AuthServer")
    fun delete(@PathVariable("id") id: Long): ResponseEntity<Void> = ResponseEntity.ok().build()
}