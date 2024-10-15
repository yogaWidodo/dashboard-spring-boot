package co.id.bankbsi.dashboardumroh.dashboardumroh.model

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table


@Entity
@Table(name = "api_key")
data class ApiKey(
    @Id
    @Column(name = "id")
    val apiKey: String
)
