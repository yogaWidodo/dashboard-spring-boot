package co.id.bankbsi.dashboardumroh.dashboardumroh.service.impl.usermanag

import co.id.bankbsi.dashboardumroh.dashboardumroh.error.DataAlreadyAssignedException
import co.id.bankbsi.dashboardumroh.dashboardumroh.error.NotFoundException
import co.id.bankbsi.dashboardumroh.dashboardumroh.model.entity.usermanag.*
import co.id.bankbsi.dashboardumroh.dashboardumroh.model.request.role.CreateRoleRequest
import co.id.bankbsi.dashboardumroh.dashboardumroh.model.request.role.ListRoleRequest
import co.id.bankbsi.dashboardumroh.dashboardumroh.model.request.role.UpdateRoleRequest
import co.id.bankbsi.dashboardumroh.dashboardumroh.model.response.usermanag.ApprovalResponse
import co.id.bankbsi.dashboardumroh.dashboardumroh.model.response.usermanag.RoleResponse
import co.id.bankbsi.dashboardumroh.dashboardumroh.repository.usermanag.ApprovalRepository
import co.id.bankbsi.dashboardumroh.dashboardumroh.repository.usermanag.AuditrailRepository
import co.id.bankbsi.dashboardumroh.dashboardumroh.repository.usermanag.RoleRepository
import co.id.bankbsi.dashboardumroh.dashboardumroh.repository.usermanag.UserRepository
import co.id.bankbsi.dashboardumroh.dashboardumroh.service.usermanag.RoleService
import co.id.bankbsi.dashboardumroh.dashboardumroh.validation.ValidationUtill
import org.springframework.data.domain.PageRequest
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import java.util.*
import java.util.stream.Collectors

@Service
class RoleServiceImpl(
    private val roleRepository: RoleRepository,
    private val approvalRepository: ApprovalRepository,
    private val userRepository: UserRepository,
    private val auditrailRepository: AuditrailRepository,
    private val validationUtill: ValidationUtill,
) : RoleService {
//    override fun create(createRoleRequest: CreateRoleRequest, userLdap: String): RoleResponse {
//        validationUtill.validate(createRoleRequest)
//        isRoleExist(createRoleRequest.namaRole)
//        val role = Role(
//            namaRole = createRoleRequest.namaRole
//        )
//        val menus = menuRepository.findAllById(createRoleRequest.idMenu)
//        role.menus = role.menus.plus(menus)
//        roleRepository.save(role)
//        return role.mapToRoleResponse()
//    }

    override fun create(createRoleRequest: CreateRoleRequest, userLdap: String): ApprovalResponse {
        validationUtill.validate(createRoleRequest)
        isRoleExist(createRoleRequest.namaRole)
        val user = userRepository.findByUserLdap(userLdap) ?: throw NotFoundException()


        val dataAfter = "{namaRole: ${createRoleRequest.namaRole}, idMenu: ${createRoleRequest.idMenu}}"
        val approval = Approval(
            maker = user.userLdap,
            approver = "",
            status = "Pending",
            typeData = "Add Role",
            dataBefore = "",
            dataAfter = dataAfter,
            remarkApproval = "",
            createAt = Date(),
            updateAt = Date()
        )
        val auditrail = Auditrail(
            createAt = Date(),
            typeData = "Add Role",
            dataBefore = "",
            dataAfter = dataAfter
        )
        auditrailRepository.save(auditrail)
        approvalRepository.save(approval)
        return approval.mapToApprovalResponse()
    }

    override fun list(listRoleRequest: ListRoleRequest): List<RoleResponse> {
        val page = roleRepository.findAll(PageRequest.of(listRoleRequest.page, listRoleRequest.size))
        val role = page.get().collect(Collectors.toList())
        return role.map { it.mapToRoleResponse() }
    }

    override fun update(idRole: Int, updateRoleRequest: UpdateRoleRequest): RoleResponse {
        val role = findRoleByIdOrThrowNotFound(idRole)
        validationUtill.validate(updateRoleRequest)
        role.apply {
            this.namaRole = updateRoleRequest.namaRole
        }
        roleRepository.save(role)
        return role.mapToRoleResponse()
    }


    override fun get(idRole: Int): RoleResponse {
        val role = findRoleByIdOrThrowNotFound(idRole)
        return role.mapToRoleResponse()
    }




    private fun findRoleByIdOrThrowNotFound(idRole: Int): Role {
        val role = roleRepository.findByIdOrNull(idRole)
        if (role == null) {
            throw NotFoundException()
        } else {
            return role
        }

    }

    private fun isRoleExist(userLdap: String): Boolean {
        if (roleRepository.findByNamaRole(userLdap) != null) {
            throw DataAlreadyAssignedException()
        }
        return false
    }


}
