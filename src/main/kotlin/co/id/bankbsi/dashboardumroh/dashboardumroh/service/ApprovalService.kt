package co.id.bankbsi.dashboardumroh.dashboardumroh.service

import co.id.bankbsi.dashboardumroh.dashboardumroh.model.request.approval.CreateApprovalRequest
import co.id.bankbsi.dashboardumroh.dashboardumroh.model.request.approval.ListApprovalRequest
import co.id.bankbsi.dashboardumroh.dashboardumroh.model.request.approval.UpdateApprovalRequest
import co.id.bankbsi.dashboardumroh.dashboardumroh.model.response.ApprovalResponse

interface ApprovalService {
    fun create(createApprovalRequest: CreateApprovalRequest):ApprovalResponse
    fun listApproval(listApprovalRequest: ListApprovalRequest):List<ApprovalResponse>
    fun update(id:String, updateApprovalRequest: UpdateApprovalRequest):ApprovalResponse
    fun delete(id:String):ApprovalResponse
    fun get(id:String):ApprovalResponse
}