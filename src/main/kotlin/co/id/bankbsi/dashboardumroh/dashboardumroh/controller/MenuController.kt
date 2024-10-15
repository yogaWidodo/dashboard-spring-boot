package co.id.bankbsi.dashboardumroh.dashboardumroh.controller

import co.id.bankbsi.dashboardumroh.dashboardumroh.model.request.menu.CreateMenuRequest
import co.id.bankbsi.dashboardumroh.dashboardumroh.model.request.menu.ListMenuRequest
import co.id.bankbsi.dashboardumroh.dashboardumroh.model.response.MenuResponse
import co.id.bankbsi.dashboardumroh.dashboardumroh.model.response.WebResponse
import co.id.bankbsi.dashboardumroh.dashboardumroh.service.MenuService
import org.springframework.web.bind.annotation.*

@RestController
class MenuController(val menuService: MenuService) {

    @PostMapping(
        value = ["/api/menu"],
        produces = ["application/json"],
        consumes = ["application/json"]
    )

    fun createMenu(@RequestBody body: CreateMenuRequest): WebResponse<MenuResponse> {
        val menuResponse = menuService.create(body)
        return WebResponse(
            code = 200,
            status = "OK",
            data = menuResponse
        )
    }

    @GetMapping(
        value = ["/api/menu"],
        produces = ["application/json"]
    )
    fun listMenu(
        @RequestParam(value = "size", defaultValue = "10") size: Int,
        @RequestParam(value = "page", defaultValue = "0") page: Int
    ):WebResponse<List<MenuResponse>>{
        val request = ListMenuRequest(page, size)
        val response = menuService.list(request)

        return WebResponse(
            code = 200,
            status="Succesfull",
            data = response
        )
    }
}