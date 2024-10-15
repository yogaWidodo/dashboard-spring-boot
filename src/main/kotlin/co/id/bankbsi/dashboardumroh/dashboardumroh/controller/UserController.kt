package co.id.bankbsi.dashboardumroh.dashboardumroh.controller

import co.id.bankbsi.dashboardumroh.dashboardumroh.model.request.user.CreateUserRequest
import co.id.bankbsi.dashboardumroh.dashboardumroh.model.request.user.ListUserRequest
import co.id.bankbsi.dashboardumroh.dashboardumroh.model.response.UserResponse
import co.id.bankbsi.dashboardumroh.dashboardumroh.model.response.WebResponse
import co.id.bankbsi.dashboardumroh.dashboardumroh.service.UserService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
class UserController(val userService: UserService) {

    @PostMapping(
        value = ["/api/user"],
        produces = ["application/json"],
        consumes = ["application/json"]
    )
    fun createUser(@RequestBody body: CreateUserRequest): WebResponse<UserResponse> {
        val userResponse = userService.create(body)
        return WebResponse(
            code = 200,
            status = "OK",
            data = userResponse
        )
    }

    @GetMapping(
        value = ["/api/user"],
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