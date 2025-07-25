package co.id.bankbsi.dashboardumroh.dashboardumroh.controller.usermanag

import co.id.bankbsi.dashboardumroh.dashboardumroh.model.request.role.CreateRoleRequest
import co.id.bankbsi.dashboardumroh.dashboardumroh.model.request.role.ListRoleRequest
import co.id.bankbsi.dashboardumroh.dashboardumroh.model.request.role.UpdateRoleRequest
import co.id.bankbsi.dashboardumroh.dashboardumroh.model.response.usermanag.ApprovalResponse
import co.id.bankbsi.dashboardumroh.dashboardumroh.model.response.usermanag.RoleResponse
import co.id.bankbsi.dashboardumroh.dashboardumroh.model.response.WebResponse
import co.id.bankbsi.dashboardumroh.dashboardumroh.service.usermanag.RoleService
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("/api/role")
@CrossOrigin(originPatterns = ["*"])
//@PreAuthorize("hasRole('ADMIN') or hasRole('OFFICER') or hasRole('SPV') or hasRole('REPORTING')")
class RoleController(val roleService: RoleService) {

    @PostMapping(
        value = ["/{userLdap}"],
        produces = ["application/json"],
        consumes = ["application/json"]
    )
    fun createRole(
        @RequestBody body: CreateRoleRequest,
        @PathVariable userLdap: String
    ): WebResponse<ApprovalResponse> {
        val roleResponse = roleService.create(body, userLdap)
        return WebResponse(
            code = 200,
            status = "OK",
            data = roleResponse
        )
    }

    @GetMapping("/{idRole}")
    fun get(@PathVariable idRole: Int): WebResponse<RoleResponse> {
        val roleResponse = roleService.get(idRole)
        return WebResponse(
            code = 200,
            status = "OK",
            data = roleResponse
        )
    }

    @PutMapping(
        value = ["/{idRole}"],
        produces = ["application/json"],
        consumes = ["application/json"]
    )
    fun updateRole(
        @PathVariable idRole: Int,
        @RequestBody updateRoleRequest: UpdateRoleRequest
    ): WebResponse<RoleResponse> {
        val roleResponse = roleService.update(idRole, updateRoleRequest)
        return WebResponse(
            code = 200,
            status = "Data Updated",
            data = roleResponse
        )
    }

//    @PutMapping(
//        value = ["role/{idRole}/menu"],
//        produces = ["application/json"],
//        consumes = ["application/json"]
//    )
//    fun updateRoleMenu(
//        @PathVariable idRole: Int,
//        @RequestBody updateRoleMenuRequest: UpdateRoleMenuRequest
//    ): WebResponse<RoleResponse> {
//        val roleResponse = roleService.updateMenus(idRole, updateRoleMenuRequest)
//        return WebResponse(
//            code = 200,
//            status = "Data Updated",
//            data = roleResponse
//        )
//    }

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    fun listRole(
        @RequestParam(value = "size", defaultValue = "10") size: Int,
        @RequestParam(value = "page", defaultValue = "0") page: Int
    ): WebResponse<List<RoleResponse>> {
        val request = ListRoleRequest(page, size)
        val response = roleService.list(request)
        return WebResponse(
            code = 200,
            status = "Succesfull",
            data = response
        )
    }
}