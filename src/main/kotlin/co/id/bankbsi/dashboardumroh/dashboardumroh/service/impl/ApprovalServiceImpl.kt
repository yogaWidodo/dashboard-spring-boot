package co.id.bankbsi.dashboardumroh.dashboardumroh.service.impl

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
        val page = approvalRepository.findAll(PageRequest.of(listApprovalRequest.page, listApprovalRequest.size))
        val approval = page.get().collect(Collectors.toList())
        return approval.map { convertApprovaltoApprovalResponse(it) }
    }

    override fun update(id: String, updateApprovalRequest: UpdateApprovalRequest): ApprovalResponse {
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
        return convertApprovaltoApprovalResponse(approval)
    }

    override fun delete(id: String): ApprovalResponse {
        val approval = findApprovalByIdOrThrowNotFound(id)
        approvalRepository.delete(approval)
        return convertApprovaltoApprovalResponse(approval)
    }

    override fun get(id: String): ApprovalResponse {
        val approval = findApprovalByIdOrThrowNotFound(id)
        return convertApprovaltoApprovalResponse(approval)
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

    private fun findApprovalByIdOrThrowNotFound(id: String): Approval {
        val approval = approvalRepository.findByIdOrNull(id)
        if (approval == null) {
            throw NotFoundException()
        } else {
            return approval
        }
    }
}