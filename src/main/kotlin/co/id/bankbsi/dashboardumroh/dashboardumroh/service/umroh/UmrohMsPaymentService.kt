package co.id.bankbsi.dashboardumroh.dashboardumroh.service.umroh

import co.id.bankbsi.dashboardumroh.dashboardumroh.model.request.umroh.mapacc.MapAccListRequest
import co.id.bankbsi.dashboardumroh.dashboardumroh.model.request.umroh.paymentrequest.PaymentListRequest
import co.id.bankbsi.dashboardumroh.dashboardumroh.model.request.umroh.paymentrequest.UmrohMsPaymentRequest
import co.id.bankbsi.dashboardumroh.dashboardumroh.model.response.umroh.UmrohMsPaymentResponse

interface UmrohMsPaymentService {
    fun create(umrohMsPaymentRequest: UmrohMsPaymentRequest):UmrohMsPaymentResponse
    fun get(id: String): UmrohMsPaymentResponse
    fun list(listRequest: PaymentListRequest):List<UmrohMsPaymentResponse>
//    fun update(id: String, umrohMsPaymentUpdate: UmrohMsPaymentUpdate):UmrohMsPaymentResponse
    fun delete(id: String)
}