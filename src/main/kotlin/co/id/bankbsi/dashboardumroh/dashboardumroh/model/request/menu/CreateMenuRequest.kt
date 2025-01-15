package co.id.bankbsi.dashboardumroh.dashboardumroh.model.request.menu

import jakarta.validation.constraints.NotBlank


data class CreateMenuRequest (

    @field:NotBlank
    val namaMenu:String,

    @field:NotBlank
    val status: String,

)