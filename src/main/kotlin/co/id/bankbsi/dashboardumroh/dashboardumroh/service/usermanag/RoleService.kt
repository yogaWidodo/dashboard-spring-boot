package co.id.bankbsi.dashboardumroh.dashboardumroh.service.usermanag

import co.id.bankbsi.dashboardumroh.dashboardumroh.model.request.role.CreateRoleRequest
import co.id.bankbsi.dashboardumroh.dashboardumroh.model.request.role.ListRoleRequest
import co.id.bankbsi.dashboardumroh.dashboardumroh.model.request.role.UpdateRoleRequest
import co.id.bankbsi.dashboardumroh.dashboardumroh.model.response.usermanag.ApprovalResponse
import co.id.bankbsi.dashboardumroh.dashboardumroh.model.response.usermanag.RoleResponse


interface RoleService {
    //    fun create(createRoleRequest: CreateRoleRequest, userLdap: String): RoleResponse
    fun create(createRoleRequest: CreateRoleRequest, userLdap: String): ApprovalResponse
    fun list(listRoleRequest: ListRoleRequest): List<RoleResponse>
    fun update(idRole: Int, updateRoleRequest: UpdateRoleRequest): RoleResponse

    //    fun updateMenus(idRole: Int, updateRoleMenuRequest: UpdateRoleMenuRequest):RoleResponse
    fun get(idRole: Int): RoleResponse
}