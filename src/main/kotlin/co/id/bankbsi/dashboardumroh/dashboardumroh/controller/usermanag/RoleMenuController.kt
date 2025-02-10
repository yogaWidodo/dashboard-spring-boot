package co.id.bankbsi.dashboardumroh.dashboardumroh.controller.usermanag

import co.id.bankbsi.dashboardumroh.dashboardumroh.model.request.role.UpdateRoleMenuRequest
import co.id.bankbsi.dashboardumroh.dashboardumroh.model.response.WebResponse
import co.id.bankbsi.dashboardumroh.dashboardumroh.service.usermanag.RoleMenuService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/role-menu")
//@PreAuthorize("hasRole('ADMIN') or hasRole('OFFICER') or hasRole('SPV') or hasRole('REPORTING')")
class RoleMenuController(
    private val roleMenuService: RoleMenuService
) {
    @PostMapping("/assign/{idRole}")
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