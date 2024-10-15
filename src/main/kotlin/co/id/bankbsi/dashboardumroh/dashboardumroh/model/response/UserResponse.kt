package co.id.bankbsi.dashboardumroh.dashboardumroh.model.response

import java.util.Date

data class UserResponse(
    val idUser:String,
    val userLdap:String,
    val nama:String,
    val idRole:String,
    val unit:String,
    val status:String,
    val createdAt:Date,
    val lastLogin:Date
)
