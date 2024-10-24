package co.id.bankbsi.dashboardumroh.dashboardumroh.service.impl

import co.id.bankbsi.dashboardumroh.dashboardumroh.model.entity.Approval
import co.id.bankbsi.dashboardumroh.dashboardumroh.model.request.approval.CreateApprovalRequest
import co.id.bankbsi.dashboardumroh.dashboardumroh.model.request.approval.ListApprovalRequest
import co.id.bankbsi.dashboardumroh.dashboardumroh.model.response.ApprovalResponse
import co.id.bankbsi.dashboardumroh.dashboardumroh.repository.ApprovalRepository
import co.id.bankbsi.dashboardumroh.dashboardumroh.service.ApprovalService
import co.id.bankbsi.dashboardumroh.dashboardumroh.validation.ValidationUtill
import org.springframework.data.domain.PageRequest
import org.springframework.stereotype.Service
import java.util.*
import java.util.stream.Collectors

@Service
class ApprovalServiceImpl(
    val approvalRepository: ApprovalRepository,
    val validationUtill: ValidationUtill
) : ApprovalService {
    override fun create(createApprovalRequest: CreateApprovalRequest): ApprovalResponse {
        validationUtill.validate(createApprovalRequest)
        val approval = Approval(
            idApproval = createApprovalRequest.idApproval,
            maker = createApprovalRequest.maker,
            approver = createApprovalRequest.approver,
            status = createApprovalRequest.status,
            typeData = createApprovalRequest.typeData,
            dataBefore = createApprovalRequest.dataBefore,
            dataAfter = createApprovalRequest.dataAfter,
            createAt = Date(),
            updateAt = Date(),
            remarkApproval = createApprovalRequest.remarkApproval
        )
        approvalRepository.save(approval)

        return convertApprovaltoApprovalResponse(approval)
    }

    override fun listApproval(listApprovalRequest: ListApprovalRequest): List<ApprovalResponse> {
        val page = approvalRepository.findAll(PageRequest.of(listApprovalRequest.page,listApprovalRequest.size))
        val approval = page.get().collect(Collectors.toList())
        return approval.map { convertApprovaltoApprovalResponse(it) }
    }


    private fun convertApprovaltoApprovalResponse(approval: Approval): ApprovalResponse {
        return ApprovalResponse(
            idApproval = approval.idApproval,
            maker = approval.maker,
            approver = approval.approver,
            status = approval.status,
            typeData = approval.typeData,
            dataBefore = approval.dataBefore,
            dataAfter = approval.dataAfter,
            createAt = approval.createAt,
            updateAt = approval.updateAt,
            remarkApproval = approval.remarkApproval
        )
    }
}