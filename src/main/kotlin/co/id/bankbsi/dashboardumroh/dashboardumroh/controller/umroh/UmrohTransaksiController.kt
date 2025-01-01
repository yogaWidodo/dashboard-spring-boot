package co.id.bankbsi.dashboardumroh.dashboardumroh.controller.umroh

import co.id.bankbsi.dashboardumroh.dashboardumroh.model.request.umroh.transaksi.TransaksiListRequest
import co.id.bankbsi.dashboardumroh.dashboardumroh.model.request.umroh.transaksi.UmrohTransaksiRequest
import co.id.bankbsi.dashboardumroh.dashboardumroh.model.request.umroh.transaksi.UmrohTransaksiUpdate
import co.id.bankbsi.dashboardumroh.dashboardumroh.model.response.WebResponse
import co.id.bankbsi.dashboardumroh.dashboardumroh.model.response.umroh.UmrohTransaksiResponse
import co.id.bankbsi.dashboardumroh.dashboardumroh.service.umroh.UmrohTransaksiService
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("/api/transaksi-umroh")
class UmrohTransaksiController(
    private val umrohTransaksiService: UmrohTransaksiService
) {

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    fun create(@RequestBody body: UmrohTransaksiRequest): WebResponse<UmrohTransaksiResponse> {
        val response = umrohTransaksiService.create(body)
        return WebResponse(
            code = 200,
            status = "OK",
            data = response
        )
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('REPORTING') or hasRole('SPV') or hasRole('ADMIN')")
    fun get(@PathVariable id: String): WebResponse<UmrohTransaksiResponse> {
        val response = umrohTransaksiService.get(id)
        return WebResponse(
            code = 200,
            status = "OK",
            data = response
        )
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    fun update(
        @PathVariable id: String,
        @RequestBody update: UmrohTransaksiUpdate
    ): WebResponse<UmrohTransaksiResponse> {
        val response = umrohTransaksiService.update(id, update)
        return WebResponse(
            code = 200,
            status = "OK",
            data = response
        )
    }


    @GetMapping
    @PreAuthorize("hasRole('REPORTING') or hasRole('SPV') or hasRole('ADMIN')")
    fun list(
        @RequestParam(value = "size", defaultValue = "10") size: Int,
        @RequestParam(value = "page", defaultValue = "0") page: Int
    ): WebResponse<List<UmrohTransaksiResponse>> {
        val request = TransaksiListRequest(page, size)
        val response = umrohTransaksiService.list(request)
        return WebResponse(
            code = 200,
            status = "OK",
            data = response
        )
    }

}