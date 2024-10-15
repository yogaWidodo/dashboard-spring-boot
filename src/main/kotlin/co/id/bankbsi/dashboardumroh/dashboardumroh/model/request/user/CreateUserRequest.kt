package co.id.bankbsi.dashboardumroh.dashboardumroh.model.request.user

import jakarta.validation.constraints.NotBlank

data class CreateUserRequest(
    @field:NotBlank
    val id_user: String,

    @field:NotBlank
    val user_ldap:String,

    @field:NotBlank
    val nama:String,

    @field:NotBlank
    val unit:String,

    @field:NotBlank
    val id_role:String,

    @field:NotBlank
    val status:String,
)