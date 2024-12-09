package co.id.bankbsi.dashboardumroh.dashboardumroh.model.response

import co.id.bankbsi.dashboardumroh.dashboardumroh.model.entity.Role
import jakarta.persistence.criteria.CriteriaBuilder.In
import java.util.Date

data class UserResponse(
    val idUser:Int,
    val userLdap:String,
    val nama:String,
    val role:Role,
    val unit:String,
    val status:String,
    val createdAt:Date,
    val lastLogin:Date,
)
