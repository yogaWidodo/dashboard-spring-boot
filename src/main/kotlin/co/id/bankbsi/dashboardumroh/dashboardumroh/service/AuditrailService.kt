package co.id.bankbsi.dashboardumroh.dashboardumroh.service

import co.id.bankbsi.dashboardumroh.dashboardumroh.model.request.auditrail.CreateAuditrailRequest
import co.id.bankbsi.dashboardumroh.dashboardumroh.model.request.auditrail.ListAuditrailRequest
import co.id.bankbsi.dashboardumroh.dashboardumroh.model.response.AuditrailResponse

interface AuditrailService {
    fun create(auditrailRequest: CreateAuditrailRequest):AuditrailResponse
    fun listAuditrail(listAuditrailRequest: ListAuditrailRequest):List<AuditrailResponse>

}