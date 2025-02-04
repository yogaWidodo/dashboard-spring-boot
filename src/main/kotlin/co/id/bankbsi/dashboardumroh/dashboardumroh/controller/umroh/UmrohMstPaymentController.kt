package co.id.bankbsi.dashboardumroh.dashboardumroh.controller.umroh

import co.id.bankbsi.dashboardumroh.dashboardumroh.model.request.umroh.paymentrequest.PaymentListRequest
import co.id.bankbsi.dashboardumroh.dashboardumroh.model.request.umroh.paymentrequest.UmrohMstPaymentRequest
import co.id.bankbsi.dashboardumroh.dashboardumroh.model.response.WebResponse
import co.id.bankbsi.dashboardumroh.dashboardumroh.model.response.umroh.UmrohMsPaymentResponse
import co.id.bankbsi.dashboardumroh.dashboardumroh.service.umroh.UmrohMstPaymentService
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/umroh-settlement")
@CrossOrigin
//@PreAuthorize("hasRole('ADMIN') or hasRole('OFFICER') or hasRole('SPV') or hasRole('REPORTING')")
class UmrohMstPaymentController(
    private val umrohMstPaymentService: UmrohMstPaymentService
) {
    @PostMapping
    fun createMsPayment(@RequestBody body:UmrohMstPaymentRequest):WebResponse<UmrohMsPaymentResponse> {
        val response = umrohMstPaymentService.create(body)
        return WebResponse(
            code = 200,
            status = "OK",
            data = response
        )
    }

    @GetMapping("/{id}")
    fun getMsPayment(@PathVariable id: String): WebResponse<UmrohMsPaymentResponse> {
        val response = umrohMstPaymentService.get(id)
        return WebResponse(
            code = 200,
            status = "OK",
            data = response
        )
    }



    @GetMapping
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