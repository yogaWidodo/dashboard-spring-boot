package co.id.bankbsi.dashboardumroh.dashboardumroh.service

import co.id.bankbsi.dashboardumroh.dashboardumroh.model.entity.Menu
import co.id.bankbsi.dashboardumroh.dashboardumroh.model.request.menu.CreateMenuRequest
import co.id.bankbsi.dashboardumroh.dashboardumroh.model.request.role.CreateRoleRequest
import co.id.bankbsi.dashboardumroh.dashboardumroh.model.request.role.ListRoleRequest
import co.id.bankbsi.dashboardumroh.dashboardumroh.model.request.role.UpdateRoleMenuRequest
import co.id.bankbsi.dashboardumroh.dashboardumroh.model.request.role.UpdateRoleRequest
import co.id.bankbsi.dashboardumroh.dashboardumroh.model.response.RoleResponse


interface RoleService {
    fun create(createroleRequest: CreateRoleRequest):RoleResponse
    fun list(listRoleRequest: ListRoleRequest):List<RoleResponse>
    fun update(id:String, updateRoleRequest: UpdateRoleRequest):RoleResponse
    fun updateMenus(id:String, updateRoleMenuRequest: UpdateRoleMenuRequest):RoleResponse
    fun delete(id:String)
    fun get(id:String):RoleResponse

}