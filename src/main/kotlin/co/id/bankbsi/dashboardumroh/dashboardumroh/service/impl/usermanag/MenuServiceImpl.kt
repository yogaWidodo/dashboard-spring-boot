package co.id.bankbsi.dashboardumroh.dashboardumroh.service.impl.usermanag

import co.id.bankbsi.dashboardumroh.dashboardumroh.error.NotFoundException
import co.id.bankbsi.dashboardumroh.dashboardumroh.model.entity.Menu
import co.id.bankbsi.dashboardumroh.dashboardumroh.model.request.menu.CreateMenuRequest
import co.id.bankbsi.dashboardumroh.dashboardumroh.model.request.menu.ListMenuRequest
import co.id.bankbsi.dashboardumroh.dashboardumroh.model.request.menu.UpdateMenuRequest
import co.id.bankbsi.dashboardumroh.dashboardumroh.model.response.MenuResponse
import co.id.bankbsi.dashboardumroh.dashboardumroh.model.response.WebResponse
import co.id.bankbsi.dashboardumroh.dashboardumroh.repository.MenuRepository
import co.id.bankbsi.dashboardumroh.dashboardumroh.service.MenuService
import co.id.bankbsi.dashboardumroh.dashboardumroh.validation.ValidationUtill
import org.springframework.data.domain.PageRequest
import org.springframework.stereotype.Service
import java.util.stream.Collectors

@Service
class MenuServiceImpl(
    val menuRepository: MenuRepository,
    val validationUtill: ValidationUtill,
) : MenuService {

    override fun create(createMenuRequest: CreateMenuRequest): MenuResponse {
        validationUtill.validate(createMenuRequest)
        val menu = Menu(
            status = createMenuRequest.status,
            namaMenu = createMenuRequest.namaMenu,
        )

        if (menuRepository.existsById(menu.idMenu)) {
            throw Exception().apply {
                WebResponse(
                    code = 400,
                    status = "BAD REQUEST",
                    data = "Menu already exist",
                )
            }
        }
        menuRepository.save(menu)
        return convertMenuToMenuResponse(menu)
    }

    override fun get(namaMenu: String): MenuResponse {
        val menu = findMenuByNamaOrThrowNotFound(namaMenu)
        return convertMenuToMenuResponse(menu)
    }

    override fun update(namaMenu: String, updateMenuRequest: UpdateMenuRequest): MenuResponse {
        val menu = findMenuByNamaOrThrowNotFound(namaMenu)
        validationUtill.validate(updateMenuRequest)
        menu.apply {
            this.namaMenu = updateMenuRequest.namaMenu
            status = updateMenuRequest.status
        }
        menuRepository.save(menu)
        return convertMenuToMenuResponse(menu)
    }

    override fun list(listMenuRequest: ListMenuRequest): List<MenuResponse> {
        val page = menuRepository.findAll(PageRequest.of(listMenuRequest.page, listMenuRequest.size))
        val menu = page.get().collect(Collectors.toList())
        return menu.map { convertMenuToMenuResponse(it) }
    }

    private fun findMenuByNamaOrThrowNotFound(namaMenu: String): Menu {
        val menu = menuRepository.findByNamaMenu(namaMenu)
        if (menu == null) {
            throw NotFoundException()
        } else {
            return menu
        }
    }


    private fun convertMenuToMenuResponse(menu: Menu): MenuResponse {
        return MenuResponse(
            idMenu = menu.idMenu,
            status = menu.status,
            namaMenu = menu.namaMenu,
        )
    }
}