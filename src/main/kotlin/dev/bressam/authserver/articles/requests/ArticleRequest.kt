package dev.bressam.authserver.articles.requests

import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Size
data class ArticleRequest(
    @field:NotBlank
    val title: String?,

    @field:NotBlank
    @field:Size(max = 240)
    val subtitle: String?,

    @field:NotBlank
    val content: String?
)
