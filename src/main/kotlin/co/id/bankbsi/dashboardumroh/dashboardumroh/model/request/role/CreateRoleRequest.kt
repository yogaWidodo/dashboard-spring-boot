package co.id.bankbsi.dashboardumroh.dashboardumroh.model.request.role

import jakarta.validation.constraints.NotBlank

data class CreateRoleRequest (

    @field:NotBlank
    val namaRole:String,

    val idMenu:MutableList<Int>,
)