package co.id.bankbsi.dashboardumroh.dashboardumroh.service.umroh

import co.id.bankbsi.dashboardumroh.dashboardumroh.model.request.umroh.listtravel.ListTravelListRequest
import co.id.bankbsi.dashboardumroh.dashboardumroh.model.request.umroh.listtravel.UmrohListTravelRequest
import co.id.bankbsi.dashboardumroh.dashboardumroh.model.request.umroh.listtravel.UmrohListTravelUpdate
import co.id.bankbsi.dashboardumroh.dashboardumroh.model.response.umroh.UmrohListTravelResponse

interface UmrohListTravelService {
    fun create(umrohListTravelRequest: UmrohListTravelRequest):UmrohListTravelResponse
    fun list(listRequest: ListTravelListRequest):List<UmrohListTravelResponse>
    fun get(id:String):UmrohListTravelResponse
    fun update(id:String, umrohListTravelUpdate: UmrohListTravelUpdate):UmrohListTravelResponse
    fun delete(id:String)
}