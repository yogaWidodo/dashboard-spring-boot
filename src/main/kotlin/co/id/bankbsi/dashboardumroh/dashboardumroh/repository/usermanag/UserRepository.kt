package co.id.bankbsi.dashboardumroh.dashboardumroh.repository.usermanag

import co.id.bankbsi.dashboardumroh.dashboardumroh.model.entity.usermanag.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface UserRepository : JpaRepository<User, Int> {
    fun findByUserLdap(userLdap: String): User?
}
