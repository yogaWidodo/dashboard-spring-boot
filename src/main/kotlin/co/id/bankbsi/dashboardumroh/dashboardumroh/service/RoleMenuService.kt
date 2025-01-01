package co.id.bankbsi.dashboardumroh.dashboardumroh.service

import co.id.bankbsi.dashboardumroh.dashboardumroh.model.request.role.UpdateRoleMenuRequest

interface RoleMenuService {
    fun assignMenuToRole(roleId: Int, menuId: UpdateRoleMenuRequest)
    fun unassignMenuToRole(roleId: Int, menuId: UpdateRoleMenuRequest)
}