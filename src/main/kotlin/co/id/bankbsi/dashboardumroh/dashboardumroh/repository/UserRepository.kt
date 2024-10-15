package co.id.bankbsi.dashboardumroh.dashboardumroh.repository

import co.id.bankbsi.dashboardumroh.dashboardumroh.entity.User
import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository:JpaRepository<User,String> {
}