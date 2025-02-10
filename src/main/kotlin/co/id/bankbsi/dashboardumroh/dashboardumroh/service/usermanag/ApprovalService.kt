package co.id.bankbsi.dashboardumroh.dashboardumroh.service.usermanag

import co.id.bankbsi.dashboardumroh.dashboardumroh.model.request.approval.CreateApprovalRequest
import co.id.bankbsi.dashboardumroh.dashboardumroh.model.request.approval.ListApprovalRequest
import co.id.bankbsi.dashboardumroh.dashboardumroh.model.request.approval.RemarkApproval
import co.id.bankbsi.dashboardumroh.dashboardumroh.model.request.approval.UpdateApprovalRequest
import co.id.bankbsi.dashboardumroh.dashboardumroh.model.response.usermanag.ApprovalResponse

interface ApprovalService {
    fun create(createApprovalRequest: CreateApprovalRequest): ApprovalResponse
    fun listApproval(listApprovalRequest: ListApprovalRequest):List<ApprovalResponse>
    fun update(id:Int, updateApprovalRequest: UpdateApprovalRequest): ApprovalResponse
    fun get(id:Int): ApprovalResponse

    fun approveChangeSettingParameter(idApproval: Int, userLdap: String): Boolean
    fun rejectChangeSettingParameter(idApproval: Int, userLdap: String, remarkApproval: RemarkApproval): Boolean

    fun approveRole(idApproval: Int, userLdap: String): Boolean
    fun rejectRole(idApproval: Int, userLdap: String, remarkApproval: RemarkApproval): Boolean

    fun approveChangeTravel(idApproval: Int, userLdap: String): Boolean
    fun rejectChangeTravel(idApproval: Int, userLdap: String, remarkApproval: RemarkApproval): Boolean

    fun approveUser(idApproval: Int, userLdap: String): Boolean
    fun rejectUser(idApproval: Int, userLdap: String, remarkApproval: RemarkApproval): Boolean

}