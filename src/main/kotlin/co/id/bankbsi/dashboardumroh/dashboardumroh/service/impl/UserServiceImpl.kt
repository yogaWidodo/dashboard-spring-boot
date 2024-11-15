package co.id.bankbsi.dashboardumroh.dashboardumroh.service.impl

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
import org.springframework.data.repository.findByIdOrNull
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
        val role = roleRepository.findById(createUserRequest.id_role).orElseThrow { NotFoundException() }

        val user = User(
            idUser = createUserRequest.id_user,
            nama = createUserRequest.nama,
            unit = createUserRequest.unit,
            idRole = role,
            status = createUserRequest.status,
            createdAt = Date(),
            lastLogin = Date(),
            userLdap = createUserRequest.user_ldap,
            passwordLdap = encoder.encode(createUserRequest.password)
        )
        userRepository.save(user)
        return convertUserToUserResponse(user)
    }

    override fun get(id: String): UserResponse {
        val user = findUserByIdOrThrowNotFound(id)
        return convertUserToUserResponse(user)
    }

    override fun list(listUserRequest: ListUserRequest): List<UserResponse> {
        val page = userRepository.findAll(PageRequest.of(listUserRequest.page, listUserRequest.size))
        val user = page.get().collect(Collectors.toList())
        return user.map { convertUserToUserResponse(it) }
    }

    override fun delete(id: String) {
        val user = findUserByIdOrThrowNotFound(id)
        userRepository.delete(user)
    }

    override fun update(id: String, updateUserRequest: UpdateUserRequest): UserResponse {
        val user = findUserByIdOrThrowNotFound(id)
        validationUtill.validate(updateUserRequest)
        user.apply {
            nama = updateUserRequest.nama
            unit = updateUserRequest.unit
            status = updateUserRequest.status
        }
        userRepository.save(user)
        return convertUserToUserResponse(user)
    }


    private fun findUserByIdOrThrowNotFound(id: String): User {
        val user = userRepository.findByIdOrNull(id)
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