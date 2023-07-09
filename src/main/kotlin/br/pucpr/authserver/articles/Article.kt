package br.pucpr.authserver.articles

import br.pucpr.authserver.users.Role
import br.pucpr.authserver.users.responses.UserResponse
import jakarta.persistence.*
import jakarta.validation.constraints.Email

@Entity
class Article(
    @Id @GeneratedValue
    var id: Long? = null,

    @Column(nullable = false)
    var title: String,

    @Column(length = 240)
    var subtitle: String,

    @Column(nullable = false)
    var content: String = ""
)