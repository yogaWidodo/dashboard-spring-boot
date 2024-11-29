package co.id.bankbsi.dashboardumroh.dashboardumroh.service.umroh

import co.id.bankbsi.dashboardumroh.dashboardumroh.model.request.umroh.detailjamaah.DetailJamaahListRequest
import co.id.bankbsi.dashboardumroh.dashboardumroh.model.request.umroh.detailjamaah.UmrohDetailJamaahRequest
import co.id.bankbsi.dashboardumroh.dashboardumroh.model.request.umroh.detailjamaah.UmrohDetailJamaahUpdate
import co.id.bankbsi.dashboardumroh.dashboardumroh.model.response.umroh.UmrohDetailJamaahResponse

interface UmrohDetailJamaahService {
    fun create (umrohDetailJamaahRequest: UmrohDetailJamaahRequest): UmrohDetailJamaahResponse
    fun get(id: String): UmrohDetailJamaahResponse
    fun list(listRequest: DetailJamaahListRequest): List<UmrohDetailJamaahResponse>
    fun update(id: String, umrohDetailJamaahUpdate: UmrohDetailJamaahUpdate): UmrohDetailJamaahResponse
    fun delete(id: String)
}