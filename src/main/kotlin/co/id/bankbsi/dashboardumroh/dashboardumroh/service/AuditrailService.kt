package co.id.bankbsi.dashboardumroh.dashboardumroh.service

import co.id.bankbsi.dashboardumroh.dashboardumroh.model.request.auditrail.CreateAuditrailRequest
import co.id.bankbsi.dashboardumroh.dashboardumroh.model.request.auditrail.ListAuditrailRequest
import co.id.bankbsi.dashboardumroh.dashboardumroh.model.request.auditrail.UpdateAuditrailRequest
import co.id.bankbsi.dashboardumroh.dashboardumroh.model.response.AuditrailResponse

interface AuditrailService {
    fun create(auditrailRequest: CreateAuditrailRequest):AuditrailResponse
    fun listAuditrail(listAuditrailRequest: ListAuditrailRequest):List<AuditrailResponse>
    fun update(id:String, auditrailRequest: UpdateAuditrailRequest):AuditrailResponse
    fun delete(id:String):AuditrailResponse
    fun get(id:String):AuditrailResponse
}