package co.id.bankbsi.dashboardumroh.dashboardumroh.model.request.role

import jakarta.validation.constraints.NotBlank

data class CreateRoleRequest (
    @field:NotBlank
    val idRole:String,

    @field:NotBlank
    val namaRole:String,

    @field:NotBlank
    val idMenu:String,
)