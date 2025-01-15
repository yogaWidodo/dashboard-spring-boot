package co.id.bankbsi.dashboardumroh.dashboardumroh.repository

import co.id.bankbsi.dashboardumroh.dashboardumroh.model.entity.usermanag.Auditrail
import org.springframework.data.jpa.repository.JpaRepository

interface AuditrailRepository:JpaRepository<Auditrail,Int> {
}