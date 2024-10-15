package co.id.bankbsi.dashboardumroh.dashboardumroh.repository

import co.id.bankbsi.dashboardumroh.dashboardumroh.model.ApiKey
import org.springframework.data.jpa.repository.JpaRepository

interface ApiKeyRepository:JpaRepository<ApiKey,String> {
}