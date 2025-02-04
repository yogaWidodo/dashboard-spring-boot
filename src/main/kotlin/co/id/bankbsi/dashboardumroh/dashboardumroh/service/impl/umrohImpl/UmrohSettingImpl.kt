package co.id.bankbsi.dashboardumroh.dashboardumroh.service.impl.umrohImpl

import co.id.bankbsi.dashboardumroh.dashboardumroh.error.NotFoundException
import co.id.bankbsi.dashboardumroh.dashboardumroh.model.entity.umroh.UmrohSetting
import co.id.bankbsi.dashboardumroh.dashboardumroh.model.entity.usermanag.Approval
import co.id.bankbsi.dashboardumroh.dashboardumroh.model.request.approval.RemarkApproval
import co.id.bankbsi.dashboardumroh.dashboardumroh.model.request.umroh.setting.SettingListRequest
import co.id.bankbsi.dashboardumroh.dashboardumroh.model.request.umroh.setting.UmrohSettingRequest
import co.id.bankbsi.dashboardumroh.dashboardumroh.model.request.umroh.setting.UmrohSettingUpdate
import co.id.bankbsi.dashboardumroh.dashboardumroh.model.response.ApprovalResponse
import co.id.bankbsi.dashboardumroh.dashboardumroh.model.response.umroh.UmrohSettingResponse
import co.id.bankbsi.dashboardumroh.dashboardumroh.repository.ApprovalRepository
import co.id.bankbsi.dashboardumroh.dashboardumroh.repository.UserRepository
import co.id.bankbsi.dashboardumroh.dashboardumroh.repository.umroh.UmrohSettingRepository
import co.id.bankbsi.dashboardumroh.dashboardumroh.service.umroh.UmrohSettingService
import co.id.bankbsi.dashboardumroh.dashboardumroh.validation.ValidationUtill
import com.google.gson.JsonParser
import org.springframework.data.domain.PageRequest
import org.springframework.stereotype.Service
import java.util.*
import java.util.stream.Collectors

@Service
class UmrohSettingImpl(
    private val umrohSettingRepository: UmrohSettingRepository,
    private val approvalRepository: ApprovalRepository,
    private val userRepository: UserRepository,
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

    override fun updateUmrohSetting(
        idSetting: Int,
        userLdap: String,
        umrohSettingUpdate: UmrohSettingUpdate,
    ): ApprovalResponse {
        val existingSetting = umrohSettingRepository.findById(idSetting).orElseThrow()
        val dataBefore =
            "{idSetting=${existingSetting.idSetting}, keySetting=${existingSetting.keySetting}, valueSetting=${existingSetting.valueSetting}}"
        val dataAfter =
            "{idSetting=${existingSetting.idSetting}, keySetting=${umrohSettingUpdate.keySetting}, valueSetting=${umrohSettingUpdate.valueSetting}}"
        val user = userRepository.findByUserLdap(userLdap) ?: throw NotFoundException()
        val approval = Approval(
            maker = user.userLdap,
            approver = "",
            status = "Pending",
            typeData = "Umroh Setting",
            dataBefore = dataBefore,
            dataAfter = dataAfter,
            createAt = Date(),
            updateAt = Date(),
            remarkApproval = ""
        )
        approvalRepository.save(approval)
        return approval.mapToApprovalResponse()
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

    private fun Approval.mapToApprovalResponse(): ApprovalResponse {
        return ApprovalResponse(
            idApproval = idApproval,
            maker = maker,
            approver = approver,
            status = status,
            typeData = typeData,
            dataBefore = dataBefore,
            dataAfter = dataAfter,
            createAt = createAt,
            updateAt = updateAt,
            remarkApproval = remarkApproval
        )
    }

    private fun UmrohSetting.toResponse() = UmrohSettingResponse(
        idSetting = this.idSetting,
        keySetting = this.keySetting,
        valueSetting = this.valueSetting
    )
}