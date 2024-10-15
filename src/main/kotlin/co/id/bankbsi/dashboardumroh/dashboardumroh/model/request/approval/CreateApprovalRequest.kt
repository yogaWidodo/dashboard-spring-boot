package co.id.bankbsi.dashboardumroh.dashboardumroh.model.request.approval

import jakarta.validation.constraints.NotBlank


data class CreateApprovalRequest(

    @field:NotBlank
    val idApproval: String,

    @field:NotBlank
    val maker:String,

    @field:NotBlank
    val approver:String,

    @field:NotBlank
    val status :String,

    @field:NotBlank
    val typeData:String,

    @field:NotBlank
    val dataBefore:String,

    @field:NotBlank
    val dataAfter:String,

    @field:NotBlank
    val remarkApproval:String
)
