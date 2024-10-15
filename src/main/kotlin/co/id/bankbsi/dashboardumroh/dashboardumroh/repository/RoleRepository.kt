package co.id.bankbsi.dashboardumroh.dashboardumroh.repository

import co.id.bankbsi.dashboardumroh.dashboardumroh.entity.Role
import org.springframework.data.jpa.repository.JpaRepository

interface RoleRepository:JpaRepository<Role,String> {
}