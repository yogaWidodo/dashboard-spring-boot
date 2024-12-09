package co.id.bankbsi.dashboardumroh.dashboardumroh.service.umroh

import co.id.bankbsi.dashboardumroh.dashboardumroh.model.request.umroh.paymentrequest.PaymentListRequest
import co.id.bankbsi.dashboardumroh.dashboardumroh.model.request.umroh.paymentrequest.UmrohMstPaymentRequest
import co.id.bankbsi.dashboardumroh.dashboardumroh.model.response.umroh.UmrohMsPaymentResponse

interface UmrohMstPaymentService {
    fun create(umrohMsPaymentRequest: UmrohMstPaymentRequest):UmrohMsPaymentResponse
    fun get(id: String): UmrohMsPaymentResponse
    fun list(listRequest: PaymentListRequest):List<UmrohMsPaymentResponse>
//    fun update(id: String, umrohMsPaymentUpdate: UmrohMsPaymentUpdate):UmrohMsPaymentResponse
    fun delete(id: String)
}