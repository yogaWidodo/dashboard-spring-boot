package co.id.bankbsi.dashboardumroh.dashboardumroh.repository

import co.id.bankbsi.dashboardumroh.dashboardumroh.model.entity.usermanag.Role
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface RoleRepository:JpaRepository<Role,Int> {
    fun findByNamaRole(namaRole:String): Role?
    fun existsByNamaRole(namaRole:String):Boolean

}