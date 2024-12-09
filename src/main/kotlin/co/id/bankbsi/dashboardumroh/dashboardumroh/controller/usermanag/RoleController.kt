package co.id.bankbsi.dashboardumroh.dashboardumroh.controller.usermanag

import co.id.bankbsi.dashboardumroh.dashboardumroh.model.request.role.CreateRoleRequest
import co.id.bankbsi.dashboardumroh.dashboardumroh.model.request.role.ListRoleRequest
import co.id.bankbsi.dashboardumroh.dashboardumroh.model.request.role.UpdateRoleMenuRequest
import co.id.bankbsi.dashboardumroh.dashboardumroh.model.request.role.UpdateRoleRequest
import co.id.bankbsi.dashboardumroh.dashboardumroh.model.response.RoleResponse
import co.id.bankbsi.dashboardumroh.dashboardumroh.model.response.WebResponse
import co.id.bankbsi.dashboardumroh.dashboardumroh.service.RoleService
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
    fun createRole(@RequestBody body: CreateRoleRequest): WebResponse<RoleResponse> {
        val roleResponse = roleService.create(body)
        return WebResponse(
            code = 200,
            status = "OK",
            data = roleResponse
        )
    }

    @GetMapping("role/{namaRole}")
    fun get(@PathVariable namaRole:String):WebResponse<RoleResponse>{
        val roleResponse = roleService.get(namaRole)
        return WebResponse(
            code = 200,
            status = "OK",
            data = roleResponse
        )
    }
    @PutMapping(
        value = ["role/{id}"],
        produces = ["application/json"],
        consumes = ["application/json"]
    )
    fun updateRole(
        @PathVariable namaRole: String,
        @RequestBody updateRoleRequest:UpdateRoleRequest
    ): WebResponse<RoleResponse> {
        val roleResponse = roleService.update(namaRole, updateRoleRequest)
        return WebResponse(
            code = 200,
            status = "Data Updated",
            data = roleResponse
        )
    }

    @PutMapping(
        value = ["role/{namaRole}/menu"],
        produces = ["application/json"],
        consumes = ["application/json"]
    )
    fun updateRoleMenu(
        @PathVariable namaRole: String,
        @RequestBody updateRoleMenuRequest: UpdateRoleMenuRequest
    ): WebResponse<RoleResponse> {
        val roleResponse = roleService.updateMenus(namaRole, updateRoleMenuRequest)
        return WebResponse(
            code = 200,
            status = "Data Updated",
            data = roleResponse
        )
    }

    @GetMapping("role")
    fun listRole(
        @RequestParam(value = "size", defaultValue = "10") size: Int,
        @RequestParam(value = "page", defaultValue = "0") page: Int
    ): WebResponse<List<RoleResponse>>{
        val request = ListRoleRequest(page, size)
        val response = roleService.list(request)

        return WebResponse(
            code = 200,
            status = "Succesfull",
            data = response
        )
    }
}