package co.id.bankbsi.dashboardumroh.dashboardumroh.controller

import co.id.bankbsi.dashboardumroh.dashboardumroh.model.request.menu.CreateMenuRequest
import co.id.bankbsi.dashboardumroh.dashboardumroh.model.request.menu.ListMenuRequest
import co.id.bankbsi.dashboardumroh.dashboardumroh.model.request.menu.UpdateMenuRequest
import co.id.bankbsi.dashboardumroh.dashboardumroh.model.response.MenuResponse
import co.id.bankbsi.dashboardumroh.dashboardumroh.model.response.WebResponse
import co.id.bankbsi.dashboardumroh.dashboardumroh.service.MenuService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/")
@CrossOrigin
class MenuController(val menuService: MenuService) {

    @PostMapping(
        value = ["menu"],
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
        value = ["menu/{id}"],
        produces = ["application/json"]
    )
    fun get(@PathVariable id: String): WebResponse<MenuResponse> {
        val menuResponse = menuService.get(id)
        return WebResponse(
            code = 200,
            status = "OK",
            data = menuResponse
        )
    }

    @PutMapping(
        value = ["menu/{id}"],
        produces = ["application/json"],
        consumes = ["application/json"]
    )
    fun updateMenu(
        @PathVariable id: String,
        @RequestBody updateMenuRequest: UpdateMenuRequest
    ): WebResponse<MenuResponse> {
        val menuResponse = menuService.update(id, updateMenuRequest)
        return WebResponse(
            code = 200,
            status = "Data Updated",
            data = menuResponse
        )
    }

    @DeleteMapping(
        value = ["menu/{id}"],
        produces = ["application/json"]
    )
    fun deleteMenu(@PathVariable id: String): WebResponse<MenuResponse> {
        val menuResponse = menuService.delete(id)
        return WebResponse(
            code = 200,
            status = "Data Deleted",
            data = menuResponse
        )
    }

    @GetMapping(
        value = ["menu"],
        produces = ["application/json"]
    )
    fun listMenu(
        @RequestParam(value = "size", defaultValue = "10") size: Int,
        @RequestParam(value = "page", defaultValue = "0") page: Int
    ): WebResponse<List<MenuResponse>> {
        val request = ListMenuRequest(page, size)
        val response = menuService.list(request)

        return WebResponse(
            code = 200,
            status = "Succesfull",
            data = response
        )
    }
}