package co.id.bankbsi.dashboardumroh.dashboardumroh.service.impl.usermanag

import co.id.bankbsi.dashboardumroh.dashboardumroh.model.entity.Role
import co.id.bankbsi.dashboardumroh.dashboardumroh.error.NotFoundException
import co.id.bankbsi.dashboardumroh.dashboardumroh.model.request.role.CreateRoleRequest
import co.id.bankbsi.dashboardumroh.dashboardumroh.model.request.role.ListRoleRequest
import co.id.bankbsi.dashboardumroh.dashboardumroh.model.request.role.UpdateRoleMenuRequest
import co.id.bankbsi.dashboardumroh.dashboardumroh.model.request.role.UpdateRoleRequest
import co.id.bankbsi.dashboardumroh.dashboardumroh.model.response.MenuResponse
import co.id.bankbsi.dashboardumroh.dashboardumroh.model.response.RoleResponse
import co.id.bankbsi.dashboardumroh.dashboardumroh.model.response.WebResponse
import co.id.bankbsi.dashboardumroh.dashboardumroh.repository.MenuRepository
import co.id.bankbsi.dashboardumroh.dashboardumroh.repository.RoleRepository
import co.id.bankbsi.dashboardumroh.dashboardumroh.service.RoleService
import co.id.bankbsi.dashboardumroh.dashboardumroh.validation.ValidationUtill
import org.springframework.data.domain.PageRequest
import org.springframework.stereotype.Service
import java.util.stream.Collectors

@Service
class RoleServiceImpl(
    val roleRepository: RoleRepository,
    val menuRepository: MenuRepository,
    val validationUtill: ValidationUtill,
) : RoleService {
    override fun create(createroleRequest: CreateRoleRequest): RoleResponse {
        validationUtill.validate(createroleRequest)
        val role = Role(
            namaRole = createroleRequest.namaRole
        )
        if (roleRepository.existsById(role.idRole)) {
            throw Exception().apply {
                WebResponse(
                    code = 400,
                    status = "BAD REQUEST",
                    data = "Role already exist",
                )
            }
        }
        val menus = menuRepository.findAllById(createroleRequest.idMenu)
        role.menus = menus
        return convertRoleToRoleResponse(roleRepository.save(role))
    }

    override fun list(listRoleRequest: ListRoleRequest): List<RoleResponse> {
        val page = roleRepository.findAll(PageRequest.of(listRoleRequest.page, listRoleRequest.size))
        val user = page.get().collect(Collectors.toList())
        return user.map { convertRoleToRoleResponse(it) }
    }

    override fun update(namaRole:String, updateRoleRequest: UpdateRoleRequest): RoleResponse {
        val user = findRoleByNamaRoleOrThrowNotFound(namaRole)
        validationUtill.validate(updateRoleRequest)
        user.apply {
            this.namaRole = updateRoleRequest.namaRole
        }
        roleRepository.save(user)
        return convertRoleToRoleResponse(user)
    }

    override fun updateMenus(namaRole:String, updateRoleMenuRequest: UpdateRoleMenuRequest): RoleResponse {
        val role = findRoleByNamaRoleOrThrowNotFound(namaRole)
        validationUtill.validate(updateRoleMenuRequest)
        role.apply {
            val oldMenu = menus.find { it.namaMenu == updateRoleMenuRequest.oldMenuNama }
            val newMenu = menuRepository.findByNamaMenu(updateRoleMenuRequest.newMenuNama)
                ?: throw NotFoundException()
            if (oldMenu != null) {
                menus.remove(oldMenu)
                menus.add(newMenu)
            }
        }
        roleRepository.save(role)
        return convertRoleToRoleResponse(roleRepository.save(role))
    }

    override fun get(namaRole:String): RoleResponse {
        val role = findRoleByNamaRoleOrThrowNotFound(namaRole)
        return convertRoleToRoleResponse(role)
    }

    private fun convertRoleToRoleResponse(role: Role): RoleResponse {
        return RoleResponse(
            idRole = role.idRole,
            namaRole = role.namaRole,
            menus = role.menus.map {
                MenuResponse(
                    idMenu = it.idMenu,
                    namaMenu = it.namaMenu,
                    status = it.status
                )
            }
        )
    }


    private fun findRoleByNamaRoleOrThrowNotFound(namaRole:String): Role {
        val role = roleRepository.findByNamaRole(namaRole)
        if (role == null) {
            throw NotFoundException()
        } else {
            return role
        }

    }
}