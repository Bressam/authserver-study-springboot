package dev.bressam.authserver.users

import org.springframework.data.jpa.repository.JpaRepository

interface RolesRepository : JpaRepository<Role, Long> {
    fun findByName(name: String): Role?
}