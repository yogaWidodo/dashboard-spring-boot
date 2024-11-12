package co.id.bankbsi.dashboardumroh.dashboardumroh.service

import co.id.bankbsi.dashboardumroh.dashboardumroh.model.entity.User
import co.id.bankbsi.dashboardumroh.dashboardumroh.model.request.user.CreateUserRequest
import co.id.bankbsi.dashboardumroh.dashboardumroh.model.request.user.ListUserRequest
import co.id.bankbsi.dashboardumroh.dashboardumroh.model.request.user.UpdateUserRequest
import co.id.bankbsi.dashboardumroh.dashboardumroh.model.response.UserResponse

interface UserService {
    fun create(createUserRequest: CreateUserRequest): UserResponse
    fun get(id: String): UserResponse
    fun list(listUserRequest: ListUserRequest): List<UserResponse>
    fun delete(id: String)
    fun update(id: String, updateUserRequest: UpdateUserRequest): UserResponse
}