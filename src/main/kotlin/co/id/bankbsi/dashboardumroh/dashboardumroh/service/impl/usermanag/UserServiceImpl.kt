package co.id.bankbsi.dashboardumroh.dashboardumroh.service.impl.usermanag

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
        val role = roleRepository.findById(createUserRequest.idRole).orElseThrow { NotFoundException() }
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
        if (userRepository.existsById(user.idUser)) {
            throw Exception("User already exist")
        }
        userRepository.save(user)
        return convertUserToUserResponse(user)
    }

    override fun get(username: String): UserResponse {
        val user = findUserByUserNameOrThrowNotFound(username)
        return convertUserToUserResponse(user)
    }

    override fun list(listUserRequest: ListUserRequest): List<UserResponse> {
        val page = userRepository.findAll(PageRequest.of(listUserRequest.page, listUserRequest.size))
        val user = page.get().collect(Collectors.toList())
        return user.map { convertUserToUserResponse(it) }
    }

    override fun update(username: String, updateUserRequest: UpdateUserRequest): UserResponse {
        val user = findUserByUserNameOrThrowNotFound(username)
        validationUtill.validate(updateUserRequest)
        user.apply {
            nama = updateUserRequest.nama
            unit = updateUserRequest.unit
            status = updateUserRequest.status
        }
        userRepository.save(user)
        return convertUserToUserResponse(user)
    }

    override fun updateLastLogin(username: String, lastLogin: Date): UserResponse {
        val user = userRepository.findByUserLdap(username)
        if (user != null) {
            user.lastLogin = lastLogin
            userRepository.save(user)
            return convertUserToUserResponse(user)
        } else {
            throw NotFoundException()
        }
    }


    private fun findUserByUserNameOrThrowNotFound(username: String): User {
        val user = userRepository.findByUserLdap(username)
        if (user == null) {
            throw NotFoundException()
        } else {
            return user
        }
    }

    private fun convertUserToUserResponse(user: User): UserResponse {
        return UserResponse(
            idUser = user.idUser,
            userLdap = user.userLdap,
            nama = user.nama,
            unit = user.unit,
            role = user.idRole,
            status = user.status,
            createdAt = user.createdAt,
            lastLogin = user.lastLogin,

            )
    }


}