package co.id.bankbsi.dashboardumroh.dashboardumroh.controller.umroh

import co.id.bankbsi.dashboardumroh.dashboardumroh.model.request.umroh.detailjamaah.DetailJamaahListRequest
import co.id.bankbsi.dashboardumroh.dashboardumroh.model.request.umroh.detailjamaah.UmrohDetailJamaahRequest
import co.id.bankbsi.dashboardumroh.dashboardumroh.model.response.WebResponse
import co.id.bankbsi.dashboardumroh.dashboardumroh.model.response.umroh.UmrohDetailJamaahResponse
import co.id.bankbsi.dashboardumroh.dashboardumroh.service.umroh.UmrohDetailJamaahService
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/detail-jamaah")
@CrossOrigin
//@PreAuthorize("hasRole('ADMIN') or hasRole('OFFICER') or hasRole('SPV') or hasRole('REPORTING')")
class UmrohDetailJamaahController(
    val service: UmrohDetailJamaahService
) {

    @PostMapping
    fun create(@RequestBody body: UmrohDetailJamaahRequest): WebResponse<UmrohDetailJamaahResponse> {
        val response = service.create(body)
        return WebResponse(
            code = 200,
            status = "OK",
            data = response
        )
    }

    @GetMapping("/{id}")
    fun get(@PathVariable id: String): WebResponse<UmrohDetailJamaahResponse> {
        val response = service.get(id)
        return WebResponse(
            code = 200,
            status = "OK",
            data = response
        )
    }



    @GetMapping
    fun getAll(
        @RequestParam(value = "size", defaultValue = "10") size: Int,
        @RequestParam(value = "page", defaultValue = "0") page: Int
    ): WebResponse<List<UmrohDetailJamaahResponse>> {
        val request = DetailJamaahListRequest(page, size)
        val response = service.list(request)
        return WebResponse(
            code = 200,
            status = "OK",
            data = response
        )
    }

}