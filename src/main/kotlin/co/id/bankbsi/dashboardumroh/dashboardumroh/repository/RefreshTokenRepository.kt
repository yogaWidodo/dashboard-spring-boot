package co.id.bankbsi.dashboardumroh.dashboardumroh.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Repository

@Repository
class RefreshTokenRepository {
    private val tokens = mutableMapOf<String, UserDetails>()

    fun findUserDetailsByToken(token: String): UserDetails? {
        return tokens[token]
    }

    fun save(token: String, userDetails: UserDetails) {
        tokens[token] = userDetails
    }
}