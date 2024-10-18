package co.id.bankbsi.dashboardumroh.dashboardumroh.service

import co.id.bankbsi.dashboardumroh.dashboardumroh.model.request.role.CreateRoleRequest
import co.id.bankbsi.dashboardumroh.dashboardumroh.model.request.role.ListRoleRequest
import co.id.bankbsi.dashboardumroh.dashboardumroh.model.response.RoleResponse


interface RoleService {
    fun create(createroleRequest: CreateRoleRequest):RoleResponse
    fun list(listRoleRequest: ListRoleRequest):List<RoleResponse>

    fun get(id:String):RoleResponse

}