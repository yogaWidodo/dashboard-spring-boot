package co.id.bankbsi.dashboardumroh.dashboardumroh.model.response.usermanag

import java.util.Date

data class AuditrailResponse(
    val idAuditrail: Int,

    val typeData: String,
    val createAt: Date,

    val dataBefore: String,

    val dataAfter: String
)