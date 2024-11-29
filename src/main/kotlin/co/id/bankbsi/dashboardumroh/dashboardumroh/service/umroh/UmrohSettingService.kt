package co.id.bankbsi.dashboardumroh.dashboardumroh.service.umroh

import co.id.bankbsi.dashboardumroh.dashboardumroh.model.request.umroh.setting.SettingListRequest
import co.id.bankbsi.dashboardumroh.dashboardumroh.model.request.umroh.setting.UmrohSettingRequest
import co.id.bankbsi.dashboardumroh.dashboardumroh.model.request.umroh.setting.UmrohSettingUpdate
import co.id.bankbsi.dashboardumroh.dashboardumroh.model.response.umroh.UmrohSettingResponse

interface UmrohSettingService {
    fun create(umrohSettingRequest: UmrohSettingRequest) : UmrohSettingResponse
    fun update(id: Int, umrohSettingUpdate: UmrohSettingUpdate) : UmrohSettingResponse
    fun get(id: Int) : UmrohSettingResponse
    fun delete(id: Int) : UmrohSettingResponse
    fun list(listRequest: SettingListRequest):List<UmrohSettingResponse>
}