package co.id.bankbsi.dashboardumroh.dashboardumroh.service.umroh

import co.id.bankbsi.dashboardumroh.dashboardumroh.model.request.umroh.setting.SettingListRequest
import co.id.bankbsi.dashboardumroh.dashboardumroh.model.request.umroh.setting.UmrohSettingRequest
import co.id.bankbsi.dashboardumroh.dashboardumroh.model.request.umroh.setting.UmrohSettingUpdate
import co.id.bankbsi.dashboardumroh.dashboardumroh.model.response.usermanag.ApprovalResponse
import co.id.bankbsi.dashboardumroh.dashboardumroh.model.response.umroh.UmrohSettingResponse

interface UmrohSettingService {
    fun create(umrohSettingRequest: UmrohSettingRequest): UmrohSettingResponse
    fun updateUmrohSetting(
        idSetting: Int,
        userLdap: String,
        umrohSettingUpdate: UmrohSettingUpdate,
    ): ApprovalResponse

    fun get(id: Int): UmrohSettingResponse
    fun delete(id: Int): UmrohSettingResponse
    fun list(listRequest: SettingListRequest): List<UmrohSettingResponse>
}