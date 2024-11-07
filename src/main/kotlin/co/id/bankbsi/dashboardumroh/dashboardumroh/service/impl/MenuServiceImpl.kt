package co.id.bankbsi.dashboardumroh.dashboardumroh.service.impl

import co.id.bankbsi.dashboardumroh.dashboardumroh.error.NotFoundException
import co.id.bankbsi.dashboardumroh.dashboardumroh.model.entity.Menu
import co.id.bankbsi.dashboardumroh.dashboardumroh.model.entity.Role
import co.id.bankbsi.dashboardumroh.dashboardumroh.model.request.menu.CreateMenuRequest
import co.id.bankbsi.dashboardumroh.dashboardumroh.model.request.menu.ListMenuRequest
import co.id.bankbsi.dashboardumroh.dashboardumroh.model.request.menu.UpdateMenuRequest
import co.id.bankbsi.dashboardumroh.dashboardumroh.model.response.MenuResponse
import co.id.bankbsi.dashboardumroh.dashboardumroh.repository.MenuRepository
import co.id.bankbsi.dashboardumroh.dashboardumroh.repository.RoleRepository
import co.id.bankbsi.dashboardumroh.dashboardumroh.service.MenuService
import co.id.bankbsi.dashboardumroh.dashboardumroh.validation.ValidationUtill
import org.springframework.data.domain.PageRequest
import org.springframework.data.repository.findByIdOrNull
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
            idMenu = createMenuRequest.idMenu,
            status = createMenuRequest.status,
            namaMenu = createMenuRequest.namaMenu,
        )
        menuRepository.save(menu)
        return convertMenuToMenuResponse(menu)
    }

    override fun get(id: String): MenuResponse {
        val menu = findMenuByIdOrThrowNotFound(id)
        return convertMenuToMenuResponse(menu)
    }

    override fun update(id: String, updateMenuRequest: UpdateMenuRequest): MenuResponse {
        val menu = findMenuByIdOrThrowNotFound(id)
        validationUtill.validate(updateMenuRequest)
        menu.apply {
            namaMenu = updateMenuRequest.namaMenu
            status = updateMenuRequest.status
        }
        menuRepository.save(menu)
        return convertMenuToMenuResponse(menu)
    }

    override fun delete(id: String): MenuResponse {
        val menu = findMenuByIdOrThrowNotFound(id)
        menuRepository.delete(menu)
        return convertMenuToMenuResponse(menu)
    }

    override fun list(listMenuRequest: ListMenuRequest): List<MenuResponse> {
        val page = menuRepository.findAll(PageRequest.of(listMenuRequest.page, listMenuRequest.size))
        val menu = page.get().collect(Collectors.toList())
        return menu.map { convertMenuToMenuResponse(it) }
    }

    private fun findMenuByIdOrThrowNotFound(id: String): Menu {
        val menu = menuRepository.findByIdOrNull(id)
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