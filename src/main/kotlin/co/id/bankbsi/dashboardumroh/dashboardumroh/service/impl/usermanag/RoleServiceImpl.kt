package co.id.bankbsi.dashboardumroh.dashboardumroh.service.impl.usermanag

import co.id.bankbsi.dashboardumroh.dashboardumroh.error.DataAlreadyAssignedException
import co.id.bankbsi.dashboardumroh.dashboardumroh.model.entity.usermanag.Role
import co.id.bankbsi.dashboardumroh.dashboardumroh.error.NotFoundException
import co.id.bankbsi.dashboardumroh.dashboardumroh.model.request.role.CreateRoleRequest
import co.id.bankbsi.dashboardumroh.dashboardumroh.model.request.role.ListRoleRequest
import co.id.bankbsi.dashboardumroh.dashboardumroh.model.request.role.UpdateRoleRequest
import co.id.bankbsi.dashboardumroh.dashboardumroh.model.response.MenuResponse
import co.id.bankbsi.dashboardumroh.dashboardumroh.model.response.RoleResponse
import co.id.bankbsi.dashboardumroh.dashboardumroh.repository.MenuRepository
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
    val menuRepository: MenuRepository,
    val validationUtill: ValidationUtill,
) : RoleService {
    override fun create(createRoleRequest: CreateRoleRequest): RoleResponse {
        validationUtill.validate(createRoleRequest)
        isRoleExist(createRoleRequest.namaRole)
        val role = Role(
            namaRole = createRoleRequest.namaRole
        )
        val menus = menuRepository.findAllById(createRoleRequest.idMenu)
        role.menus = role.menus.plus(menus)
        roleRepository.save(role)
        return role.mapToRoleResponse()
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

//    override fun updateMenus(idRole: Int, updateRoleMenuRequest: UpdateRoleMenuRequest): RoleResponse {
//        val role = findRoleByIdOrThrowNotFound(idRole)
//        validationUtill.validate(updateRoleMenuRequest)
//        role.apply {
//            val oldMenu = menus.find { it.namaMenu == updateRoleMenuRequest.oldMenuNama }
//            val newMenu = menuRepository.findByNamaMenu(updateRoleMenuRequest.newMenuNama)
//                ?: throw NotFoundException()
//            if (oldMenu != null) {
//                menus.minus(oldMenu)
//                menus.plus(newMenu)
//            }
//        }
//        roleRepository.save(role)
//        return convertRoleToRoleResponse(roleRepository.save(role))
//    }

    override fun get(idRole: Int): RoleResponse {
        val role = findRoleByIdOrThrowNotFound(idRole)
        return role.mapToRoleResponse()
    }

    private fun Role.mapToRoleResponse(): RoleResponse {
        return RoleResponse(
            idRole = this.idRole,
            namaRole = this.namaRole,
            menus = this.menus.map {
                MenuResponse(
                    idMenu = it.idMenu,
                    namaMenu = it.namaMenu,
                    status = it.status
                )
            }
        )
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