package co.id.bankbsi.dashboardumroh.dashboardumroh.controller

import co.id.bankbsi.dashboardumroh.dashboardumroh.model.request.role.CreateRoleRequest
import co.id.bankbsi.dashboardumroh.dashboardumroh.model.request.role.ListRoleRequest
import co.id.bankbsi.dashboardumroh.dashboardumroh.model.response.RoleResponse
import co.id.bankbsi.dashboardumroh.dashboardumroh.model.response.WebResponse
import co.id.bankbsi.dashboardumroh.dashboardumroh.service.RoleService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController


@RestController
class RoleController(val roleService: RoleService) {
    @PostMapping(
        value = ["/api/role"],
        produces = ["application/json"],
        consumes = ["application/json"]
    )
    fun createUser(@RequestBody body: CreateRoleRequest): WebResponse<RoleResponse> {
        val roleResponse = roleService.create(body)
        return WebResponse(
            code = 200,
            status = "OK",
            data = roleResponse
        )
    }
    @GetMapping(
        value = ["/api/role"],
        produces = ["application/json"]
    )
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