package co.id.bankbsi.dashboardumroh.dashboardumroh.controller.usermanag

import co.id.bankbsi.dashboardumroh.dashboardumroh.model.request.menu.CreateMenuRequest
import co.id.bankbsi.dashboardumroh.dashboardumroh.model.request.menu.ListMenuRequest
import co.id.bankbsi.dashboardumroh.dashboardumroh.model.request.menu.UpdateMenuRequest
import co.id.bankbsi.dashboardumroh.dashboardumroh.model.response.MenuResponse
import co.id.bankbsi.dashboardumroh.dashboardumroh.model.response.WebResponse
import co.id.bankbsi.dashboardumroh.dashboardumroh.service.MenuService
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/")
@CrossOrigin(originPatterns = ["*"])
//@PreAuthorize("hasRole('ADMIN') or hasRole('OFFICER') or hasRole('SPV') or hasRole('REPORTING')")
class MenuController(val menuService: MenuService) {

    @PostMapping(
        value = ["menu"],
        produces = ["application/json"],
        consumes = ["application/json"]
    )
    fun createMenu(@RequestBody body: CreateMenuRequest): WebResponse<MenuResponse> {
        val result = menuService.create(body)
        return WebResponse(
            code = 200,
            status = "OK",
            data = result
        )
    }

    @GetMapping(
        value = ["menu/{namaMenu}"],
        produces = ["application/json"]
    )
    fun get(@PathVariable namaMenu: String): WebResponse<MenuResponse> {
        val menuResponse = menuService.get(namaMenu)
        return WebResponse(
            code = 200,
            status = "OK",
            data = menuResponse
        )
    }

    @PutMapping(
        value = ["menu/{namaMenu}"],
        produces = ["application/json"],
        consumes = ["application/json"]
    )
    fun updateMenu(
        @PathVariable namaMenu: String,
        @RequestBody updateMenuRequest: UpdateMenuRequest
    ): WebResponse<MenuResponse> {
        val menuResponse = menuService.update(namaMenu, updateMenuRequest)
        return WebResponse(
            code = 200,
            status = "Data Updated",
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