package co.id.bankbsi.dashboardumroh.dashboardumroh.controller.umroh

import co.id.bankbsi.dashboardumroh.dashboardumroh.model.request.umroh.mapacc.MapAccListRequest
import co.id.bankbsi.dashboardumroh.dashboardumroh.model.request.umroh.mapacc.UmrohMapAccRequest
import co.id.bankbsi.dashboardumroh.dashboardumroh.model.response.WebResponse
import co.id.bankbsi.dashboardumroh.dashboardumroh.model.response.umroh.UmrohMapAccResponse
import co.id.bankbsi.dashboardumroh.dashboardumroh.service.umroh.UmrohMapAccService
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/map-acc")
@CrossOrigin
class UmrohMapAccController(private val service: UmrohMapAccService) {

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    fun createMapAcc(@RequestBody body: UmrohMapAccRequest): WebResponse<UmrohMapAccResponse> {
        val response = service.create(body)
        return WebResponse(
            code = 200,
            status = "OK",
            data = response
        )
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    fun getMapAcc(@PathVariable id: Int): WebResponse<UmrohMapAccResponse> {
        val response = service.get(id)
        return WebResponse(
            code = 200,
            status = "OK",
            data = response
        )
    }



    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    fun getAll(
        @RequestParam(value = "size", defaultValue = "10") size: Int,
        @RequestParam(value = "page", defaultValue = "0") page: Int
    ): WebResponse<List<UmrohMapAccResponse>> {
        val request = MapAccListRequest(page, size)
        val response = service.list(request)
        return WebResponse(
            code = 200,
            status = "OK",
            data = response
        )
    }
}