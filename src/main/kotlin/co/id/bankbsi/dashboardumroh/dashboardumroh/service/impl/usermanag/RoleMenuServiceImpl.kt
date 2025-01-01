package co.id.bankbsi.dashboardumroh.dashboardumroh.service.impl.usermanag

import co.id.bankbsi.dashboardumroh.dashboardumroh.error.DataAlreadyAssignedException
import co.id.bankbsi.dashboardumroh.dashboardumroh.error.NotFoundException
import co.id.bankbsi.dashboardumroh.dashboardumroh.model.request.role.UpdateRoleMenuRequest
import co.id.bankbsi.dashboardumroh.dashboardumroh.repository.MenuRepository
import co.id.bankbsi.dashboardumroh.dashboardumroh.repository.RoleRepository
import co.id.bankbsi.dashboardumroh.dashboardumroh.service.RoleMenuService
import org.springframework.stereotype.Service

@Service
class RoleMenuServiceImpl(
    private val roleRepository: RoleRepository,
    private val menuRepository: MenuRepository
) : RoleMenuService {
    override fun assignMenuToRole(roleId: Int, menuId: UpdateRoleMenuRequest) {
        val role = roleRepository.findById(roleId).orElseThrow()
        val menu = menuRepository.findAllById(menuId.idMenu)
        if (role.menus.containsAll(menu)) {
            throw DataAlreadyAssignedException()
        }
        role.menus = role.menus.plus(menu)
        roleRepository.save(role)
    }

    override fun unassignMenuToRole(roleId: Int, menuId: UpdateRoleMenuRequest) {
        val role = roleRepository.findById(roleId).orElseThrow()
        val menu = menuRepository.findAllById(menuId.idMenu)
        if (!role.menus.containsAll(menu)) {
            throw NotFoundException()
        }
        role.menus = role.menus.minus(menu)
        roleRepository.save(role)
    }
}