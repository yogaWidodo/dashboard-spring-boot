package co.id.bankbsi.dashboardumroh.dashboardumroh.service

import co.id.bankbsi.dashboardumroh.dashboardumroh.model.request.menu.CreateMenuRequest
import co.id.bankbsi.dashboardumroh.dashboardumroh.model.request.menu.ListMenuRequest
import co.id.bankbsi.dashboardumroh.dashboardumroh.model.response.MenuResponse

interface MenuService {
    fun create(createMenuRequest: CreateMenuRequest): MenuResponse
    fun list(listMenuRequest: ListMenuRequest): List<MenuResponse>
}