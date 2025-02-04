package co.id.bankbsi.dashboardumroh.dashboardumroh.controller.usermanag

import co.id.bankbsi.dashboardumroh.dashboardumroh.model.request.UpdateUserMenuRequest
import co.id.bankbsi.dashboardumroh.dashboardumroh.model.request.user.CreateUserRequest
import co.id.bankbsi.dashboardumroh.dashboardumroh.model.request.user.ListUserRequest
import co.id.bankbsi.dashboardumroh.dashboardumroh.model.request.user.UpdateUserRequest
import co.id.bankbsi.dashboardumroh.dashboardumroh.model.response.UserResponse
import co.id.bankbsi.dashboardumroh.dashboardumroh.model.response.WebResponse
import co.id.bankbsi.dashboardumroh.dashboardumroh.service.UserService
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/")
//@PreAuthorize("hasRole('ADMIN') or hasRole('OFFICER') or hasRole('SPV') or hasRole('REPORTING')")
class UserController(val userService: UserService) {

    @PostMapping("user")
    fun createUser(@RequestBody body: CreateUserRequest): WebResponse<UserResponse> {
        val userResponse = userService.create(body)
        return WebResponse(
            code = 200,
            status = "OK",
            data = userResponse
        )
    }


    @GetMapping("user/{userLdap}")
    fun get(@PathVariable userLdap: String): WebResponse<UserResponse> {
        val userResponse = userService.get(userLdap)
        return WebResponse(
            code = 200,
            status = "OK",
            data = userResponse
        )
    }

    @PutMapping(
        value = ["user/{userLdap}"],
        produces = ["application/json"],
        consumes = ["application/json"]
    )
    fun updateUser(
        @PathVariable userLdap: String,
        @RequestBody updateUserRequest: UpdateUserRequest
    ): WebResponse<UserResponse> {
        val userResponse = userService.update(userLdap, updateUserRequest)
        return WebResponse(
            code = 200,
            status = "Data Updated",
            data = userResponse
        )
    }

    @GetMapping(
        value = ["user"],
        produces = ["application/json"]
    )
    fun listUser(
        @RequestParam(value = "size", defaultValue = "10") size: Int,
        @RequestParam(value = "page", defaultValue = "0") page: Int
    ): WebResponse<List<UserResponse>> {
        val request = ListUserRequest(page, size)
        val response = userService.list(request)

        return WebResponse(
            code = 200,
            status = "Succesfull",
            data = response
        )
    }
}