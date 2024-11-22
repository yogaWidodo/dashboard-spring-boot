package co.id.bankbsi.dashboardumroh.dashboardumroh.model.request.user

import jakarta.validation.constraints.NotBlank

data class UpdateUserRequest(
    @field:NotBlank
    val nama:String,

    @field:NotBlank
    val unit:String,

    @field:NotBlank
    val status:String
)

data class UpdateLastLoginRequest(
    @field:NotBlank
    val lastLogin:String
)
