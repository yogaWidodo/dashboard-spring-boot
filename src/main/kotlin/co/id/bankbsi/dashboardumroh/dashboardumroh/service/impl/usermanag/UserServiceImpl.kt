package co.id.bankbsi.dashboardumroh.dashboardumroh.service.impl.usermanag

import co.id.bankbsi.dashboardumroh.dashboardumroh.error.DataAlreadyAssignedException
import co.id.bankbsi.dashboardumroh.dashboardumroh.error.NotFoundException
import co.id.bankbsi.dashboardumroh.dashboardumroh.model.entity.usermanag.*
import co.id.bankbsi.dashboardumroh.dashboardumroh.model.request.user.CreateUserRequest
import co.id.bankbsi.dashboardumroh.dashboardumroh.model.request.user.ListUserRequest
import co.id.bankbsi.dashboardumroh.dashboardumroh.model.request.user.UpdateUserRequest
import co.id.bankbsi.dashboardumroh.dashboardumroh.model.response.usermanag.ApprovalResponse
import co.id.bankbsi.dashboardumroh.dashboardumroh.model.response.usermanag.UserResponse
import co.id.bankbsi.dashboardumroh.dashboardumroh.repository.usermanag.ApprovalRepository
import co.id.bankbsi.dashboardumroh.dashboardumroh.repository.usermanag.AuditrailRepository
import co.id.bankbsi.dashboardumroh.dashboardumroh.repository.usermanag.RoleRepository
import co.id.bankbsi.dashboardumroh.dashboardumroh.repository.usermanag.UserRepository
import co.id.bankbsi.dashboardumroh.dashboardumroh.service.usermanag.UserService
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
    val approvalRepository: ApprovalRepository,
    val encoder: PasswordEncoder
) : UserService {

    private val formatter: SimpleDateFormat by lazy {
        SimpleDateFormat("EEEE, d MMM HH:mm:ss z yyyy", Locale("id", "ID"))
    }

//    override fun create(createUserRequest: CreateUserRequest, userLdap: String): UserResponse {
//        validationUtill.validate(createUserRequest)
//        val role = roleRepository.findById(createUserRequest.idRole).orElseThrow()
//        isUserExist(createUserRequest.userLdap)
//        val user = User(
//            nama = encoder.encode(createUserRequest.nama),
//            unit = createUserRequest.unit,
//            idRole = role,
//            status = createUserRequest.status,
//            createdAt = Date(),
//            lastLogin = Date(),
//            userLdap = createUserRequest.userLdap,
////            passwordLdap = encoder.encode(createUserRequest.password)
//        )
//        userRepository.save(user)
//        return user.mapToUserResponse()
//    }
override fun create(createUserRequest: CreateUserRequest, userLdap: String): ApprovalResponse {
    validationUtill.validate(createUserRequest)
    val role = roleRepository.findById(createUserRequest.idRole).orElseThrow()
    isUserExist(createUserRequest.userLdap)
    val user = userRepository.findByUserLdap(userLdap)?: throw NotFoundException()
    val dataAfter = mapOf(
        "nama" to createUserRequest.nama,
        "unit" to createUserRequest.unit,
        "idRole" to role.idRole,
        "status" to createUserRequest.status,
        "userLdap" to createUserRequest.userLdap
    )

    val approval = Approval(
        maker = user.userLdap,
        approver = "",
        status = "Pending",
        typeData = "Add User",
        dataBefore = "",
        dataAfter = dataAfter.toString(),
        remarkApproval = "",
        createAt = Date(),
        updateAt = Date()
    )
    approvalRepository.save(approval)
    return approval.mapToApprovalResponse()
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
}