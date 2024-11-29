package co.id.bankbsi.dashboardumroh.dashboardumroh.controller.umroh

import co.id.bankbsi.dashboardumroh.dashboardumroh.model.request.umroh.mapacc.MapAccListRequest
import co.id.bankbsi.dashboardumroh.dashboardumroh.model.request.umroh.paymentrequest.PaymentListRequest
import co.id.bankbsi.dashboardumroh.dashboardumroh.model.request.umroh.paymentrequest.UmrohMsPaymentRequest
import co.id.bankbsi.dashboardumroh.dashboardumroh.model.response.WebResponse
import co.id.bankbsi.dashboardumroh.dashboardumroh.model.response.umroh.UmrohMsPaymentResponse
import co.id.bankbsi.dashboardumroh.dashboardumroh.service.umroh.UmrohMsPaymentService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/umroh-ms-payment")
@CrossOrigin
class UmrohMsPaymentController(
    private val umrohMsPaymentService: UmrohMsPaymentService
) {
    @PostMapping
    fun createMsPayment(@RequestBody body:UmrohMsPaymentRequest):WebResponse<UmrohMsPaymentResponse> {
        val response = umrohMsPaymentService.create(body)
        return WebResponse(
            code = 200,
            status = "OK",
            data = response
        )
    }

    @GetMapping("/{id}")
    fun getMsPayment(@PathVariable id: String): WebResponse<UmrohMsPaymentResponse> {
        val response = umrohMsPaymentService.get(id)
        return WebResponse(
            code = 200,
            status = "OK",
            data = response
        )
    }

    @DeleteMapping("/{id}")
    fun deleteMsPayment(@PathVariable id: String): WebResponse<String> {
        umrohMsPaymentService.delete(id)
        return WebResponse(
            code = 200,
            status = "OK",
            data = "Data $id has been deleted"
        )
    }

    @GetMapping
    fun listMsPayment(
        @RequestParam(value = "size", defaultValue = "10") size: Int,
        @RequestParam(value = "page", defaultValue = "0") page: Int
    ): WebResponse<List<UmrohMsPaymentResponse>> {
        val request = PaymentListRequest(page, size)
        val response = umrohMsPaymentService.list(request)
        return WebResponse(
            code = 200,
            status = "OK",
            data = response
        )
    }
}