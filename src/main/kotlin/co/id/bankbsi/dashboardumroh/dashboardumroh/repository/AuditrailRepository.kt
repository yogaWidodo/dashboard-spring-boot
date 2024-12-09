package co.id.bankbsi.dashboardumroh.dashboardumroh.repository

import co.id.bankbsi.dashboardumroh.dashboardumroh.model.entity.Auditrail
import org.springframework.data.jpa.repository.JpaRepository

interface AuditrailRepository:JpaRepository<Auditrail,Int> {
}