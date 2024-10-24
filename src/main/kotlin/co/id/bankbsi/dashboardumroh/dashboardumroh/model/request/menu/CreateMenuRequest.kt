package co.id.bankbsi.dashboardumroh.dashboardumroh.model.request.menu

import co.id.bankbsi.dashboardumroh.dashboardumroh.model.entity.Role
import jakarta.validation.constraints.NotBlank


data class CreateMenuRequest (

    @field:NotBlank
    val idMenu: String,

    @field:NotBlank
    val namaMenu:String,

    @field:NotBlank
    val status: String,

)