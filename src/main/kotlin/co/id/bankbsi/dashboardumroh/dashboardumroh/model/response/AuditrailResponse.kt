package co.id.bankbsi.dashboardumroh.dashboardumroh.model.response

import jakarta.validation.constraints.NotBlank
import java.util.Date

data class AuditrailResponse(
    val idAuditrail: String,

    val typeData: String,
    val createAt: Date,

    val dataBefore: String,

    val dataAfter: String
)