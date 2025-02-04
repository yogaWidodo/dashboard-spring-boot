package co.id.bankbsi.dashboardumroh.dashboardumroh.repository

import co.id.bankbsi.dashboardumroh.dashboardumroh.model.entity.usermanag.Menu
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface MenuRepository:JpaRepository<Menu,Int> {
    fun findByNamaMenu(namaMenu: String): Menu?
    fun existsByNamaMenu(namaMenu: String): Boolean

    @Query("SELECT m FROM Menu m ORDER BY m.idMenu")
    fun getAllMenu(): List<Menu>
}