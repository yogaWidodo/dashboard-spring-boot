package co.id.bankbsi.dashboardumroh.dashboardumroh.controller.umroh

import co.id.bankbsi.dashboardumroh.dashboardumroh.model.request.umroh.listtravel.ListTravelListRequest
import co.id.bankbsi.dashboardumroh.dashboardumroh.model.request.umroh.listtravel.UmrohListTravelRequest
import co.id.bankbsi.dashboardumroh.dashboardumroh.model.request.umroh.listtravel.UmrohListTravelUpdate
import co.id.bankbsi.dashboardumroh.dashboardumroh.model.response.WebResponse
import co.id.bankbsi.dashboardumroh.dashboardumroh.model.response.umroh.UmrohListTravelResponse
import co.id.bankbsi.dashboardumroh.dashboardumroh.service.umroh.UmrohListTravelService
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/travel")
@CrossOrigin
//@PreAuthorize("hasRole('ADMIN') or hasRole('OFFICER') or hasRole('SPV') or hasRole('REPORTING')")
class UmrohListTravelController(
    private val service: UmrohListTravelService
) {
    @PostMapping
    fun createTravel(@RequestBody body: UmrohListTravelRequest): WebResponse<UmrohListTravelResponse> {
        val response = service.create(body)
        return WebResponse(
            code = 200,
            status = "OK",
            data = response
        )
    }

    @GetMapping("/{id}")
    fun getTravel(@PathVariable id: String): WebResponse<UmrohListTravelResponse> {
        val response = service.get(id)
        return WebResponse(
            code = 200,
            status = "OK",
            data = response
        )
    }

    @PutMapping("/{id}")
    fun updateTravel(
        @PathVariable id: String,
        @RequestBody body: UmrohListTravelUpdate
    ): WebResponse<UmrohListTravelResponse> {
        val response = service.update(id, body)
        return WebResponse(
            code = 200,
            status = "OK",
            data = response
        )
    }

    @GetMapping
    fun getAllTravel(
        @RequestParam(value = "size", defaultValue = "10") size: Int,
        @RequestParam(value = "page", defaultValue = "0") page: Int
    ): WebResponse<List<UmrohListTravelResponse>> {
        val request = ListTravelListRequest(page, size)
        val response = service.list(request)
        return WebResponse(
            code = 200,
            status = "OK",
            data = response
        )

    }
}