package co.id.bankbsi.dashboardumroh.dashboardumroh.service.impl.usermanag

import co.id.bankbsi.dashboardumroh.dashboardumroh.error.DataAlreadyAssignedException
import co.id.bankbsi.dashboardumroh.dashboardumroh.model.entity.User
import co.id.bankbsi.dashboardumroh.dashboardumroh.error.NotFoundException
import co.id.bankbsi.dashboardumroh.dashboardumroh.model.request.user.CreateUserRequest
import co.id.bankbsi.dashboardumroh.dashboardumroh.model.request.user.ListUserRequest
import co.id.bankbsi.dashboardumroh.dashboardumroh.model.request.user.UpdateUserRequest
import co.id.bankbsi.dashboardumroh.dashboardumroh.model.response.UserResponse
import co.id.bankbsi.dashboardumroh.dashboardumroh.repository.RoleRepository
import co.id.bankbsi.dashboardumroh.dashboardumroh.repository.UserRepository
import co.id.bankbsi.dashboardumroh.dashboardumroh.service.UserService
import co.id.bankbsi.dashboardumroh.dashboardumroh.validation.ValidationUtill
import org.springframework.data.domain.PageRequest
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import java.util.*
import java.util.stream.Collectors

@Service
class UserServiceImpl(
    val userRepository: UserRepository,
    val validationUtill: ValidationUtill,
    val roleRepository: RoleRepository,
    val encoder: PasswordEncoder
) : UserService {

    override fun create(createUserRequest: CreateUserRequest): UserResponse {
        validationUtill.validate(createUserRequest)
        val role = roleRepository.findById(createUserRequest.idRole).orElseThrow()
        isUserExist(createUserRequest.userLdap)
        val user = User(
            nama = createUserRequest.nama,
            unit = createUserRequest.unit,
            idRole = role,
            status = createUserRequest.status,
            createdAt = Date(),
            lastLogin = Date(),
            userLdap = createUserRequest.userLdap,
            passwordLdap = encoder.encode(createUserRequest.password)
        )
        userRepository.save(user)
        return user.mapToUserResponse()
    }

    override fun get(userLdap: String): UserResponse {
        val user = findUserByUserNameOrThrowNotFound(userLdap)
        return user.mapToUserResponse()
    }

    override fun list(listUserRequest: ListUserRequest): List<UserResponse> {
        val page = userRepository.findAll(PageRequest.of(listUserRequest.page, listUserRequest.size))
        val user = page.get().collect(Collectors.toList())
        return user.map { it.mapToUserResponse() }
    }

    override fun update(userLdap: String, updateUserRequest: UpdateUserRequest): UserResponse {
        val user = findUserByUserNameOrThrowNotFound(userLdap)
        validationUtill.validate(updateUserRequest)
        user.apply {
            nama = updateUserRequest.nama
            unit = updateUserRequest.unit
            status = updateUserRequest.status

        }
        userRepository.save(user)
        return user.mapToUserResponse()
    }

    override fun updateLastLogin(userLdap: String, lastLogin: Date): UserResponse {
        val user = userRepository.findByUserLdap(userLdap)
        if (user != null) {
            user.lastLogin = lastLogin
            userRepository.save(user)
            return user.mapToUserResponse()
        } else {
            throw NotFoundException()
        }
    }


    private fun findUserByUserNameOrThrowNotFound(userLdap: String): User {
        val user = userRepository.findByUserLdap(userLdap)
        if (user == null) {
            throw NotFoundException()
        } else {
            return user
        }
    }

    private fun isUserExist(userLdap: String): Boolean {
        if (userRepository.findByUserLdap(userLdap) != null) {
            throw DataAlreadyAssignedException()
        }
        return false
    }

    private fun User.mapToUserResponse(): UserResponse {
        return UserResponse(
            idUser = this.idUser,
            userLdap = this.userLdap,
            nama = this.nama,
            unit = this.unit,
            role = this.idRole,
            status = this.status,
            createdAt = this.createdAt,
            lastLogin = this.lastLogin,
        )
    }


}