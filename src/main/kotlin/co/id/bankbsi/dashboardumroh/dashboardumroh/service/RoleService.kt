package co.id.bankbsi.dashboardumroh.dashboardumroh.service

import co.id.bankbsi.dashboardumroh.dashboardumroh.model.request.role.CreateRoleRequest
import co.id.bankbsi.dashboardumroh.dashboardumroh.model.request.role.ListRoleRequest
import co.id.bankbsi.dashboardumroh.dashboardumroh.model.request.role.UpdateRoleRequest
import co.id.bankbsi.dashboardumroh.dashboardumroh.model.response.RoleResponse


interface  RoleService {
    fun create(createRoleRequest: CreateRoleRequest):RoleResponse
    fun list(listRoleRequest: ListRoleRequest):List<RoleResponse>
    fun update(idRole:Int, updateRoleRequest: UpdateRoleRequest):RoleResponse
//    fun updateMenus(idRole: Int, updateRoleMenuRequest: UpdateRoleMenuRequest):RoleResponse
    fun get(idRole: Int):RoleResponse
}