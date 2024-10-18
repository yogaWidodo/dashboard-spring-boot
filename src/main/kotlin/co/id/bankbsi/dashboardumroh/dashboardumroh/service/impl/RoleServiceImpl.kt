package co.id.bankbsi.dashboardumroh.dashboardumroh.service.impl

import co.id.bankbsi.dashboardumroh.dashboardumroh.model.entity.Role
import co.id.bankbsi.dashboardumroh.dashboardumroh.model.entity.User
import co.id.bankbsi.dashboardumroh.dashboardumroh.error.NotFoundException
import co.id.bankbsi.dashboardumroh.dashboardumroh.model.request.role.CreateRoleRequest
import co.id.bankbsi.dashboardumroh.dashboardumroh.model.request.role.ListRoleRequest
import co.id.bankbsi.dashboardumroh.dashboardumroh.model.response.RoleResponse
import co.id.bankbsi.dashboardumroh.dashboardumroh.repository.RoleRepository
import co.id.bankbsi.dashboardumroh.dashboardumroh.service.RoleService
import co.id.bankbsi.dashboardumroh.dashboardumroh.validation.ValidationUtill
import org.springframework.data.domain.PageRequest
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import java.util.stream.Collectors

@Service
class RoleServiceImpl(
    val roleRepository: RoleRepository,
    val validationUtill: ValidationUtill
) : RoleService {
    override fun create(createroleRequest: CreateRoleRequest): RoleResponse {
        validationUtill.validate(createroleRequest)

        val role = Role(
            idRole = createroleRequest.idRole,
            namaRole = createroleRequest.namaRole,
            idMenu = createroleRequest.idMenu
        )
        roleRepository.save(role)
        return convertRoleToRoleResponse(role)
    }

    override fun list(listRoleRequest: ListRoleRequest): List<RoleResponse> {
        val page = roleRepository.findAll(PageRequest.of(listRoleRequest.page, listRoleRequest.size))
        val user = page.get().collect(Collectors.toList())
        return user.map { convertRoleToRoleResponse(it) }
    }

    override fun get(id: String): RoleResponse {
        val role = findRoleByIdOrThrowNotFound(id)
        return convertRoleToRoleResponse(role)
    }

    private fun convertRoleToRoleResponse(role: Role): RoleResponse {
        return RoleResponse(
            idRole = role.idRole,
            namaRole = role.namaRole,
            idMenu = role.idMenu
        )
    }


    private fun findRoleByIdOrThrowNotFound(id: String): Role {
        val role = roleRepository.findByIdOrNull(id)
        if (role == null) {
            throw NotFoundException()
        } else {
            return role;
        }
    }
}