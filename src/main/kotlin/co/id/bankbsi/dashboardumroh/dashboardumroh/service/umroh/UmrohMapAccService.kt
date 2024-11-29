package co.id.bankbsi.dashboardumroh.dashboardumroh.service.umroh

import co.id.bankbsi.dashboardumroh.dashboardumroh.model.request.umroh.mapacc.MapAccListRequest
import co.id.bankbsi.dashboardumroh.dashboardumroh.model.request.umroh.mapacc.UmrohMapAccRequest
import co.id.bankbsi.dashboardumroh.dashboardumroh.model.request.umroh.mapacc.UmrohMapAccUpdate
import co.id.bankbsi.dashboardumroh.dashboardumroh.model.response.umroh.UmrohMapAccResponse

interface UmrohMapAccService {
    fun create(umrohMapAccRequest: UmrohMapAccRequest): UmrohMapAccResponse
    fun get(id: Int): UmrohMapAccResponse
    fun list(listRequest: MapAccListRequest): List<UmrohMapAccResponse>
    fun update(id: Int, umrohMapAccUpdate: UmrohMapAccUpdate): UmrohMapAccResponse
    fun delete(id: Int)
}