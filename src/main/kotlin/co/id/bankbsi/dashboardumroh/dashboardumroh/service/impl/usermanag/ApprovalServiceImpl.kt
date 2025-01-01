package co.id.bankbsi.dashboardumroh.dashboardumroh.service.impl.usermanag

import co.id.bankbsi.dashboardumroh.dashboardumroh.error.NotFoundException
import co.id.bankbsi.dashboardumroh.dashboardumroh.model.entity.Approval
import co.id.bankbsi.dashboardumroh.dashboardumroh.model.request.approval.CreateApprovalRequest
import co.id.bankbsi.dashboardumroh.dashboardumroh.model.request.approval.ListApprovalRequest
import co.id.bankbsi.dashboardumroh.dashboardumroh.model.request.approval.UpdateApprovalRequest
import co.id.bankbsi.dashboardumroh.dashboardumroh.model.response.ApprovalResponse
import co.id.bankbsi.dashboardumroh.dashboardumroh.repository.ApprovalRepository
import co.id.bankbsi.dashboardumroh.dashboardumroh.service.ApprovalService
import co.id.bankbsi.dashboardumroh.dashboardumroh.validation.ValidationUtill
import org.springframework.data.domain.PageRequest
import org.springframework.data.repository.findByIdOrNull
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
        return approval.mapToApprovalResponse()
    }

    override fun listApproval(listApprovalRequest: ListApprovalRequest): List<ApprovalResponse> {
        val page = approvalRepository.findAll(PageRequest.of(listApprovalRequest.page, listApprovalRequest.size))
        val approval = page.get().collect(Collectors.toList())
        return approval.map { it.mapToApprovalResponse() }
    }

    override fun update(id: Int, updateApprovalRequest: UpdateApprovalRequest): ApprovalResponse {
        val approval = findApprovalByIdOrThrowNotFound(id)
        validationUtill.validate(updateApprovalRequest)
        approval.apply {
            maker = updateApprovalRequest.maker
            approver = updateApprovalRequest.approver
            status = updateApprovalRequest.status
            typeData = updateApprovalRequest.typeData
            dataBefore = updateApprovalRequest.dataBefore
            dataAfter = updateApprovalRequest.dataAfter
            updateAt = Date()
            remarkApproval = updateApprovalRequest.remarkApproval
        }
        approvalRepository.save(approval)
        return approval.mapToApprovalResponse()
    }



    override fun get(id: Int): ApprovalResponse {
        val approval = findApprovalByIdOrThrowNotFound(id)
        return approval.mapToApprovalResponse()
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

    private fun findApprovalByIdOrThrowNotFound(id: Int): Approval {
        val approval = approvalRepository.findByIdOrNull(id)
        if (approval == null) {
            throw NotFoundException()
        } else {
            return approval
        }
    }
}