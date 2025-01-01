package co.id.bankbsi.dashboardumroh.dashboardumroh.service.impl.usermanag

import co.id.bankbsi.dashboardumroh.dashboardumroh.error.DataAlreadyAssignedException
import co.id.bankbsi.dashboardumroh.dashboardumroh.error.NotFoundException
import co.id.bankbsi.dashboardumroh.dashboardumroh.model.entity.Menu
import co.id.bankbsi.dashboardumroh.dashboardumroh.model.request.menu.CreateMenuRequest
import co.id.bankbsi.dashboardumroh.dashboardumroh.model.request.menu.ListMenuRequest
import co.id.bankbsi.dashboardumroh.dashboardumroh.model.request.menu.UpdateMenuRequest
import co.id.bankbsi.dashboardumroh.dashboardumroh.model.response.MenuResponse
import co.id.bankbsi.dashboardumroh.dashboardumroh.repository.MenuRepository
import co.id.bankbsi.dashboardumroh.dashboardumroh.service.MenuService
import co.id.bankbsi.dashboardumroh.dashboardumroh.validation.ValidationUtill
import org.springframework.data.domain.PageRequest
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException
import java.util.stream.Collectors

@Service
class MenuServiceImpl(
    val menuRepository: MenuRepository,
    val validationUtill: ValidationUtill,
) : MenuService {

    override fun create(createMenuRequest: CreateMenuRequest): MenuResponse {
        validationUtill.validate(createMenuRequest)
        isMenuExist(createMenuRequest.namaMenu)
        val menu = Menu(
            status = createMenuRequest.status,
            namaMenu = createMenuRequest.namaMenu,
        )
        menuRepository.save(menu)
        return menu.mapToMenuResponse()
    }

    override fun get(namaMenu: String): MenuResponse {
        val menu = findMenuByNamaOrThrowNotFound(namaMenu)
        return menu.mapToMenuResponse()
    }

    override fun update(namaMenu: String, updateMenuRequest: UpdateMenuRequest): MenuResponse {
        val menu = findMenuByNamaOrThrowNotFound(namaMenu)
        validationUtill.validate(updateMenuRequest)
        menu.apply {
            this.namaMenu = updateMenuRequest.namaMenu
            status = updateMenuRequest.status
        }
        menuRepository.save(menu)
        return menu.mapToMenuResponse()
    }

    override fun list(listMenuRequest: ListMenuRequest): List<MenuResponse> {
        val page = menuRepository.findAll(PageRequest.of(listMenuRequest.page, listMenuRequest.size))
        val menu = page.get().collect(Collectors.toList())
        return menu.map { it.mapToMenuResponse() }
    }

    private fun findMenuByNamaOrThrowNotFound(namaMenu: String): Menu {
        val menu = menuRepository.findByNamaMenu(namaMenu)
        if (menu == null) {
            throw NotFoundException()
        } else {
            return menu
        }
    }


    private fun isMenuExist(namaMenu: String) {
        if (menuRepository.existsByNamaMenu(namaMenu)) {
            throw DataAlreadyAssignedException()
        }
    }
    private fun Menu.mapToMenuResponse(): MenuResponse {
        return MenuResponse(
            idMenu = idMenu,
            status = status,
            namaMenu = namaMenu,
        )
    }
}