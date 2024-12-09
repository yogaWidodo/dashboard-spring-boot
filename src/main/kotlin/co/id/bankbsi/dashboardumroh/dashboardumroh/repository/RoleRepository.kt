package co.id.bankbsi.dashboardumroh.dashboardumroh.repository

import co.id.bankbsi.dashboardumroh.dashboardumroh.model.entity.Role
import jakarta.persistence.criteria.CriteriaBuilder.In
import org.springframework.data.jpa.repository.JpaRepository

interface RoleRepository:JpaRepository<Role,Int> {
    fun findByNamaRole(namaRole:String):Role?
}