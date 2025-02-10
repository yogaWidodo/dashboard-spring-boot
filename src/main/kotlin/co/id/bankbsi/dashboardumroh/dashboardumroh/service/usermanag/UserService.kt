package co.id.bankbsi.dashboardumroh.dashboardumroh.service.usermanag

import co.id.bankbsi.dashboardumroh.dashboardumroh.model.request.user.CreateUserRequest
import co.id.bankbsi.dashboardumroh.dashboardumroh.model.request.user.ListUserRequest
import co.id.bankbsi.dashboardumroh.dashboardumroh.model.request.user.UpdateUserRequest
import co.id.bankbsi.dashboardumroh.dashboardumroh.model.response.usermanag.ApprovalResponse
import co.id.bankbsi.dashboardumroh.dashboardumroh.model.response.usermanag.UserResponse
import jakarta.servlet.http.HttpServletRequest
import java.util.Date

interface UserService {
    fun create(createUserRequest: CreateUserRequest, userLdap: String): ApprovalResponse
    fun get(userLdap: String): UserResponse
    fun list(listUserRequest: ListUserRequest): List<UserResponse>
    fun update(userLdap: String, updateUserRequest: UpdateUserRequest): UserResponse
    fun updateLastLogin(userLdap: String, lastLogin: Date, request: HttpServletRequest): UserResponse
}