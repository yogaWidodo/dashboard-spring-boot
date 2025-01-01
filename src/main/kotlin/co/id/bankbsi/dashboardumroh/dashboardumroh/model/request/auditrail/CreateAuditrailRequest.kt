package co.id.bankbsi.dashboardumroh.dashboardumroh.model.request.auditrail

import jakarta.validation.constraints.NotBlank

data class CreateAuditrailRequest(
    @field:NotBlank
    val typeData: String,

    @field:NotBlank
    val dataBefore: String,

    @field:NotBlank
    val dataAfter: String
)