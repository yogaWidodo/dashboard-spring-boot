package co.id.bankbsi.dashboardumroh.dashboardumroh.service.impl

import co.id.bankbsi.dashboardumroh.dashboardumroh.model.entity.User
import co.id.bankbsi.dashboardumroh.dashboardumroh.error.NotFoundException
import co.id.bankbsi.dashboardumroh.dashboardumroh.model.request.user.CreateUserRequest
import co.id.bankbsi.dashboardumroh.dashboardumroh.model.request.user.ListUserRequest
import co.id.bankbsi.dashboardumroh.dashboardumroh.model.response.UserResponse
import co.id.bankbsi.dashboardumroh.dashboardumroh.repository.UserRepository
import co.id.bankbsi.dashboardumroh.dashboardumroh.service.UserService
import co.id.bankbsi.dashboardumroh.dashboardumroh.validation.ValidationUtill
import org.springframework.data.domain.PageRequest
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import java.util.*
import java.util.stream.Collectors

@Service
class UserServiceImpl(
    val userRepository: UserRepository,
    val validationUtill: ValidationUtill
) : UserService {
    override fun create(createUserRequest: CreateUserRequest): UserResponse {
        validationUtill.validate(createUserRequest)
        val user = User(
            idUser = createUserRequest.id_user,
            userLdap = createUserRequest.user_ldap,
            nama = createUserRequest.nama,
            unit = createUserRequest.unit,
            idRole = createUserRequest.id_role,
            status = createUserRequest.status,
            createdAt = Date(),
            lastLogin = Date()
        )
        userRepository.save(user)
        return convertUserToUserResponse(user)
    }

    override fun get(id: String): UserResponse {
        val user = findUserByIdOrThrowNotFound(id)
        return convertUserToUserResponse(user)
    }

    override fun list(listUserRequest: ListUserRequest): List<UserResponse> {
        val page = userRepository.findAll(PageRequest.of(listUserRequest.page,listUserRequest.size))
        val user = page.get().collect(Collectors.toList())
        return user.map { convertUserToUserResponse(it) }
    }


    private fun findUserByIdOrThrowNotFound(id: String): User {
        val user = userRepository.findByIdOrNull(id)
        if (user == null) {
            throw NotFoundException()
        } else {
            return user;
        }
    }

    private fun convertUserToUserResponse(user: User): UserResponse {
        return UserResponse(
            idUser = user.idUser,
            userLdap = user.userLdap,
            nama = user.nama,
            unit = user.unit,
            idRole = user.idRole,
            status = user.status,
            createdAt = user.createdAt,
            lastLogin = user.lastLogin
        )
    }
}