package co.id.bankbsi.dashboardumroh.dashboardumroh.service

import co.id.bankbsi.dashboardumroh.dashboardumroh.model.request.approval.CreateApprovalRequest
import co.id.bankbsi.dashboardumroh.dashboardumroh.model.request.approval.ListApprovalRequest
import co.id.bankbsi.dashboardumroh.dashboardumroh.model.request.approval.RemarkApproval
import co.id.bankbsi.dashboardumroh.dashboardumroh.model.request.approval.UpdateApprovalRequest
import co.id.bankbsi.dashboardumroh.dashboardumroh.model.response.ApprovalResponse
import com.google.gson.JsonParser
import java.util.*

interface ApprovalService {
    fun create(createApprovalRequest: CreateApprovalRequest):ApprovalResponse
    fun listApproval(listApprovalRequest: ListApprovalRequest):List<ApprovalResponse>
    fun update(id:Int, updateApprovalRequest: UpdateApprovalRequest):ApprovalResponse
    fun get(id:Int):ApprovalResponse
    fun approveChangeSettingParameter(idApproval: Int, userLdap: String): Boolean
    fun rejectChangeSettingParameter(idApproval: Int, userLdap: String, remarkApproval: RemarkApproval): Boolean
}