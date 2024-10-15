package co.id.bankbsi.dashboardumroh.dashboardumroh.model.response

import jakarta.persistence.Column

data class MenuResponse(
    val idMenu: String,
    val namaMenu:String,
    val status: String
)