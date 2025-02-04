package co.id.bankbsi.dashboardumroh.dashboardumroh.repository

import co.id.bankbsi.dashboardumroh.dashboardumroh.model.entity.usermanag.Auditrail
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface AuditrailRepository : JpaRepository<Auditrail, Int> {
    @Query("SELECT a FROM Auditrail a ORDER BY a.createAt DESC")
    fun findAllAuditrailByCreateAtDesc(pageable: Pageable): Page<Auditrail>
}