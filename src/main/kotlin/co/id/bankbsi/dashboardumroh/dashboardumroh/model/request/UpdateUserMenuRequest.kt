package co.id.bankbsi.dashboardumroh.dashboardumroh.model.request

import jakarta.validation.constraints.NotBlank

data class UpdateUserMenuRequest(
    @field:NotBlank
    val menuName: String
)
