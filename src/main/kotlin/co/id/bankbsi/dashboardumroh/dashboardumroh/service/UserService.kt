package co.id.bankbsi.dashboardumroh.dashboardumroh.service

import co.id.bankbsi.dashboardumroh.dashboardumroh.model.request.UpdateUserMenuRequest
import co.id.bankbsi.dashboardumroh.dashboardumroh.model.request.user.CreateUserRequest
import co.id.bankbsi.dashboardumroh.dashboardumroh.model.request.user.ListUserRequest
import co.id.bankbsi.dashboardumroh.dashboardumroh.model.request.user.UpdateUserRequest
import co.id.bankbsi.dashboardumroh.dashboardumroh.model.response.UserResponse
import java.util.Date

interface UserService {
    fun create(createUserRequest: CreateUserRequest): UserResponse
    fun get(userLdap: String): UserResponse
    fun list(listUserRequest: ListUserRequest): List<UserResponse>
    fun update(userLdap: String, updateUserRequest: UpdateUserRequest): UserResponse
    fun updateLastLogin(userLdap: String, lastLogin: Date): UserResponse
}