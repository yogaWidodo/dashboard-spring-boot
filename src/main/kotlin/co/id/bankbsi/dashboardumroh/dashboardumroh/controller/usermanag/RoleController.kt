package co.id.bankbsi.dashboardumroh.dashboardumroh.controller.usermanag

import co.id.bankbsi.dashboardumroh.dashboardumroh.model.request.role.CreateRoleRequest
import co.id.bankbsi.dashboardumroh.dashboardumroh.model.request.role.ListRoleRequest
import co.id.bankbsi.dashboardumroh.dashboardumroh.model.request.role.UpdateRoleRequest
import co.id.bankbsi.dashboardumroh.dashboardumroh.model.response.RoleResponse
import co.id.bankbsi.dashboardumroh.dashboardumroh.model.response.WebResponse
import co.id.bankbsi.dashboardumroh.dashboardumroh.service.RoleService
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("/api/")
@CrossOrigin(originPatterns = ["*"])
class RoleController(val roleService: RoleService) {
    @PostMapping(
        value = ["role"],
        produces = ["application/json"],
        consumes = ["application/json"]
    )
    @PreAuthorize("hasRole('ADMIN')")
    fun createRole(@RequestBody body: CreateRoleRequest): WebResponse<RoleResponse> {
        val roleResponse = roleService.create(body)
        return WebResponse(
            code = 200,
            status = "OK",
            data = roleResponse
        )
    }

    @GetMapping("role/{idRole}")
    @PreAuthorize("hasRole('ADMIN')")
    fun get(@PathVariable idRole: Int): WebResponse<RoleResponse> {
        val roleResponse = roleService.get(idRole)
        return WebResponse(
            code = 200,
            status = "OK",
            data = roleResponse
        )
    }

    @PutMapping(
        value = ["role/{idRole}"],
        produces = ["application/json"],
        consumes = ["application/json"]
    )
    @PreAuthorize("hasRole('ADMIN')")
    fun updateRole(
        @PathVariable idRole:  Int,
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

    @GetMapping("role")
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