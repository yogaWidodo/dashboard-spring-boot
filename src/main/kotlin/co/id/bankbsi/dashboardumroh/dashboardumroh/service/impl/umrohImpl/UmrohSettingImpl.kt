package co.id.bankbsi.dashboardumroh.dashboardumroh.service.impl.umrohImpl

import co.id.bankbsi.dashboardumroh.dashboardumroh.model.entity.umroh.UmrohSetting
import co.id.bankbsi.dashboardumroh.dashboardumroh.model.request.umroh.setting.SettingListRequest
import co.id.bankbsi.dashboardumroh.dashboardumroh.model.request.umroh.setting.UmrohSettingRequest
import co.id.bankbsi.dashboardumroh.dashboardumroh.model.request.umroh.setting.UmrohSettingUpdate
import co.id.bankbsi.dashboardumroh.dashboardumroh.model.response.umroh.UmrohSettingResponse
import co.id.bankbsi.dashboardumroh.dashboardumroh.repository.umroh.UmrohSettingRepository
import co.id.bankbsi.dashboardumroh.dashboardumroh.service.umroh.UmrohSettingService
import co.id.bankbsi.dashboardumroh.dashboardumroh.validation.ValidationUtill
import org.springframework.data.domain.PageRequest
import org.springframework.stereotype.Service
import java.util.stream.Collectors

@Service
class UmrohSettingImpl(
    private val umrohSettingRepository: UmrohSettingRepository,
    private val validationUtill: ValidationUtill
) : UmrohSettingService {
    override fun create(umrohSettingRequest: UmrohSettingRequest): UmrohSettingResponse {
        validationUtill.validate(umrohSettingRequest)
        val umrohSetting = UmrohSetting(
            idSetting = umrohSettingRequest.idSetting,
            keySetting = umrohSettingRequest.keySetting,
            valueSetting = umrohSettingRequest.valueSetting
        )
        umrohSettingRepository.save(umrohSetting)
        return umrohSetting.toResponse()
    }

    override fun update(id: Int, umrohSettingUpdate: UmrohSettingUpdate): UmrohSettingResponse {
        val umrohSetting = findUmrohSettingByIdOrThrowNotFound(id)
        validationUtill.validate(umrohSettingUpdate)
        umrohSetting.apply {
            keySetting = umrohSettingUpdate.keySetting
            valueSetting = umrohSettingUpdate.valueSetting
        }
        umrohSettingRepository.save(umrohSetting)
        return umrohSetting.toResponse()
    }

    override fun get(id: Int): UmrohSettingResponse {
        val setting = findUmrohSettingByIdOrThrowNotFound(id)
        return setting.toResponse()
    }

    override fun delete(id: Int): UmrohSettingResponse {
        val setting = findUmrohSettingByIdOrThrowNotFound(id)
        umrohSettingRepository.delete(setting)
        return setting.toResponse()
    }

    override fun list(listRequest: SettingListRequest): List<UmrohSettingResponse> {
        val page = umrohSettingRepository.findAll(PageRequest.of(listRequest.page, listRequest.size))
        val setting = page.get().collect(Collectors.toList())
        return setting.map {
            it.toResponse()
        }
    }

    private fun findUmrohSettingByIdOrThrowNotFound(id: Int): UmrohSetting {
        return umrohSettingRepository.findById(id).orElseThrow {
            IllegalArgumentException("Umroh Setting not found")
        }
    }

    private fun UmrohSetting.toResponse()= UmrohSettingResponse(
        idSetting = this.idSetting,
        keySetting = this.keySetting,
        valueSetting = this.valueSetting
    )
}