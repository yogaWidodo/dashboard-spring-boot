package co.id.bankbsi.dashboardumroh.dashboardumroh.repository.usermanag

import co.id.bankbsi.dashboardumroh.dashboardumroh.model.entity.usermanag.Role
import org.springframework.data.jpa.repository.JpaRepository

interface RoleRepository:JpaRepository<Role,Int> {
    fun findByNamaRole(namaRole:String): Role?
    fun existsByNamaRole(namaRole:String):Boolean

}