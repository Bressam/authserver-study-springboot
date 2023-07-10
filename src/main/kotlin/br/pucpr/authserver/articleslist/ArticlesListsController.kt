package br.pucpr.authserver.articleslist


import br.pucpr.authserver.articleslist.requests.ArticlesListRequest
import br.pucpr.authserver.articleslist.responses.ArticlesListResponse
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.Parameter
import io.swagger.v3.oas.annotations.security.SecurityRequirement
import jakarta.transaction.Transactional
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/articlesLists")
class ArticlesListsController(private val service: ArticlesListsService) {
    @Operation(
        summary = "List all articles lists"
    )
    @GetMapping
    fun listArticles(): List<ArticlesListResponse> {
        return service.findAll().map { it.toResponse() }
    }
    @Operation(
        summary = "Get articles list by id",
        parameters = [
            Parameter(
            name = "id",
            description = "Articles list ID",
            example = "1"
            )
        ]
    )
    @GetMapping("/{id}")
    fun getArticlesList(@PathVariable("id") id: Long) =
        service.findById(id)
            ?.let { ResponseEntity.ok(it.toResponse()) }
            ?: ResponseEntity.notFound().build()

    @Transactional
    @PatchMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    fun updateArticlesList(@RequestBody req: ArticlesListRequest, @PathVariable("id") id: Long) {
        service.updateArticlesList(id, req)
            ?.let { ResponseEntity.ok() }
            ?: ResponseEntity.status(HttpStatus.NO_CONTENT)
    }

    @Transactional
    @PostMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    fun createArticlesList(@Valid @RequestBody req: ArticlesListRequest): ResponseEntity<ArticlesListResponse> {
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