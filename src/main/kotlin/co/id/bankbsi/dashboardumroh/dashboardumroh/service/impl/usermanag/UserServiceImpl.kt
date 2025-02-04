package co.id.bankbsi.dashboardumroh.dashboardumroh.service.impl.usermanag

import co.id.bankbsi.dashboardumroh.dashboardumroh.error.DataAlreadyAssignedException
import co.id.bankbsi.dashboardumroh.dashboardumroh.model.entity.usermanag.User
import co.id.bankbsi.dashboardumroh.dashboardumroh.error.NotFoundException
import co.id.bankbsi.dashboardumroh.dashboardumroh.model.entity.usermanag.Auditrail
import co.id.bankbsi.dashboardumroh.dashboardumroh.model.request.user.CreateUserRequest
import co.id.bankbsi.dashboardumroh.dashboardumroh.model.request.user.ListUserRequest
import co.id.bankbsi.dashboardumroh.dashboardumroh.model.request.user.UpdateUserRequest
import co.id.bankbsi.dashboardumroh.dashboardumroh.model.response.UserResponse
import co.id.bankbsi.dashboardumroh.dashboardumroh.repository.AuditrailRepository
import co.id.bankbsi.dashboardumroh.dashboardumroh.repository.RoleRepository
import co.id.bankbsi.dashboardumroh.dashboardumroh.repository.UserRepository
import co.id.bankbsi.dashboardumroh.dashboardumroh.service.UserService
import co.id.bankbsi.dashboardumroh.dashboardumroh.validation.ValidationUtill
import jakarta.servlet.http.HttpServletRequest
import org.springframework.data.domain.PageRequest
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import java.text.SimpleDateFormat
import java.util.*
import java.util.stream.Collectors

@Service
class UserServiceImpl(
    val userRepository: UserRepository,
    val validationUtill: ValidationUtill,
    val roleRepository: RoleRepository,
    val auditrailRepository: AuditrailRepository,
    val encoder: PasswordEncoder
) : UserService {

    private val formatter: SimpleDateFormat by lazy {
        SimpleDateFormat("EEEE, d MMM HH:mm:ss z yyyy", Locale("id", "ID"))
    }
    override fun create(createUserRequest: CreateUserRequest): UserResponse {
        validationUtill.validate(createUserRequest)
        val role = roleRepository.findById(createUserRequest.idRole).orElseThrow()
        isUserExist(createUserRequest.userLdap)
        val user = User(
            nama = encoder.encode(createUserRequest.nama),
            unit = createUserRequest.unit,
            idRole = role,
            status = createUserRequest.status,
            createdAt = Date(),
            lastLogin = Date(),
            userLdap = createUserRequest.userLdap,
//            passwordLdap = encoder.encode(createUserRequest.password)
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

    override fun updateLastLogin(userLdap: String, lastLogin: Date, request: HttpServletRequest): UserResponse {
        val user = userRepository.findByUserLdap(userLdap)

        if (user != null) {
            val ipAddress = request.getHeader("X-FORWARDED-FOR") ?: request.remoteAddr
            val auditrail = Auditrail(
                createAt = Date(),
                typeData = "${user.userLdap} | $ipAddress",
                dataBefore = formatter.format(user.lastLogin),
                dataAfter = formatter.format(lastLogin)
            )
            user.lastLogin = lastLogin
            userRepository.save(user)
            auditrailRepository.save(auditrail)
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