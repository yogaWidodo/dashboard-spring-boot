package co.id.bankbsi.dashboardumroh.dashboardumroh.model.response

import java.util.*

data class ApprovalResponse (

    val idApproval: Int,

    val maker:String,

    val approver:String,

    val status :String,

    val typeData:String,

    val dataBefore:String,

    val dataAfter:String,

    val createAt: Date,

    val updateAt:Date,

    val remarkApproval:String
)