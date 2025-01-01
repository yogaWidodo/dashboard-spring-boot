package co.id.bankbsi.dashboardumroh.dashboardumroh.controller.umroh

import co.id.bankbsi.dashboardumroh.dashboardumroh.model.request.umroh.paymentrequest.PaymentListRequest
import co.id.bankbsi.dashboardumroh.dashboardumroh.model.request.umroh.paymentrequest.UmrohMstPaymentRequest
import co.id.bankbsi.dashboardumroh.dashboardumroh.model.response.WebResponse
import co.id.bankbsi.dashboardumroh.dashboardumroh.model.response.umroh.UmrohMsPaymentResponse
import co.id.bankbsi.dashboardumroh.dashboardumroh.service.umroh.UmrohMstPaymentService
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/umroh-mstpayment")
@CrossOrigin
class UmrohMstPaymentController(
    private val umrohMstPaymentService: UmrohMstPaymentService
) {
    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    fun createMsPayment(@RequestBody body:UmrohMstPaymentRequest):WebResponse<UmrohMsPaymentResponse> {
        val response = umrohMstPaymentService.create(body)
        return WebResponse(
            code = 200,
            status = "OK",
            data = response
        )
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    fun getMsPayment(@PathVariable id: String): WebResponse<UmrohMsPaymentResponse> {
        val response = umrohMstPaymentService.get(id)
        return WebResponse(
            code = 200,
            status = "OK",
            data = response
        )
    }



    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    fun listMsPayment(
        @RequestParam(value = "size", defaultValue = "10") size: Int,
        @RequestParam(value = "page", defaultValue = "0") page: Int
    ): WebResponse<List<UmrohMsPaymentResponse>> {
        val request = PaymentListRequest(page, size)
        val response = umrohMstPaymentService.list(request)
        return WebResponse(
            code = 200,
            status = "OK",
            data = response
        )
    }
}