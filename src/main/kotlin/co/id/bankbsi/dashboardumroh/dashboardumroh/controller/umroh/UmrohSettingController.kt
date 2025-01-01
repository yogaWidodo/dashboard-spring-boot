package co.id.bankbsi.dashboardumroh.dashboardumroh.controller.umroh

import co.id.bankbsi.dashboardumroh.dashboardumroh.model.request.umroh.setting.SettingListRequest
import co.id.bankbsi.dashboardumroh.dashboardumroh.model.request.umroh.setting.UmrohSettingRequest
import co.id.bankbsi.dashboardumroh.dashboardumroh.model.request.umroh.setting.UmrohSettingUpdate
import co.id.bankbsi.dashboardumroh.dashboardumroh.model.response.WebResponse
import co.id.bankbsi.dashboardumroh.dashboardumroh.model.response.umroh.UmrohSettingResponse
import co.id.bankbsi.dashboardumroh.dashboardumroh.service.umroh.UmrohSettingService
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping("/api/settings")
@CrossOrigin
class UmrohSettingController(
    private val settingService: UmrohSettingService
) {
    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    fun createSetting(@RequestBody body: UmrohSettingRequest): WebResponse<UmrohSettingResponse> {
        val response = settingService.create(body)
        return WebResponse(
            code = 200,
            status = "OK",
            data = response
        )
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    fun get(@PathVariable id: Int): WebResponse<UmrohSettingResponse> {
        val response = settingService.get(id)
        return WebResponse(
            code = 200,
            status = "OK",
            data = response
        )
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    fun updateSetting(
        @PathVariable id: Int,
        @RequestBody body: UmrohSettingUpdate
    ): WebResponse<UmrohSettingResponse> {
        val response = settingService.update(id, body)
        return WebResponse(
            code = 200,
            status = "OK",
            data = response
        )
    }


    @GetMapping
    @PreAuthorize("hasRole('OFFICER') or hasRole('ADMIN')")
    fun getAll(
        @RequestParam(value = "size", defaultValue = "10") size: Int,
        @RequestParam(value = "page", defaultValue = "0") page: Int
    ): WebResponse<List<UmrohSettingResponse>> {
        val request = SettingListRequest(page, size)
        val response = settingService.list(request)

        return WebResponse(
            code = 200,
            status = "OK",
            data = response
        )
    }

}