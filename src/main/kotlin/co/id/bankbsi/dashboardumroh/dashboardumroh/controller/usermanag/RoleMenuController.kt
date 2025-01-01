package co.id.bankbsi.dashboardumroh.dashboardumroh.controller.usermanag

import co.id.bankbsi.dashboardumroh.dashboardumroh.model.request.role.UpdateRoleMenuRequest
import co.id.bankbsi.dashboardumroh.dashboardumroh.model.response.WebResponse
import co.id.bankbsi.dashboardumroh.dashboardumroh.service.RoleMenuService
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/role-menu")
class RoleMenuController(
    private val roleMenuService: RoleMenuService
) {
    @PostMapping("/assign/{idRole}")
    @PreAuthorize("hasRole('ADMIN')")
    fun assignMenuToRole(
        @PathVariable idRole: Int,
        @RequestBody request: UpdateRoleMenuRequest
    ): WebResponse<String> {
        roleMenuService.assignMenuToRole(idRole, request)
        return WebResponse(
            code = 200,
            status = "OK",
            data = "Id Menu${request.idMenu} assigned to role with id $idRole"
        )
    }

    @PostMapping("/unassign/{idRole}")
    @PreAuthorize("hasRole('ADMIN')")
    fun unassignMenuToRole(
        @PathVariable idRole: Int,
        @RequestBody request: UpdateRoleMenuRequest
    ): WebResponse<String> {
        roleMenuService.unassignMenuToRole(idRole, request)
        return WebResponse(
            code = 200,
            status = "OK",
            data = "Id Menu${request.idMenu} unassigned to role with id $idRole"

        )
    }


}