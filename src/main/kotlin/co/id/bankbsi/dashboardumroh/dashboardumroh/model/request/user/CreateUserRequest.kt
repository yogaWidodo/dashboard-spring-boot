package co.id.bankbsi.dashboardumroh.dashboardumroh.model.request.user

import jakarta.validation.constraints.NotBlank

data class CreateUserRequest(

    @field:NotBlank
    val userLdap:String,

    @field:NotBlank
    val nama:String,

    @field:NotBlank
    val unit:String,

    val idRole:Int,

    @field:NotBlank
    val status:String,

//    @field:NotBlank
//    val password:String,
)