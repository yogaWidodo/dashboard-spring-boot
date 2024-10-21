package co.id.bankbsi.dashboardumroh.dashboardumroh.controller

import co.id.bankbsi.dashboardumroh.dashboardumroh.model.request.approval.CreateApprovalRequest
import co.id.bankbsi.dashboardumroh.dashboardumroh.model.request.approval.ListApprovalRequest
import co.id.bankbsi.dashboardumroh.dashboardumroh.model.response.ApprovalResponse
import co.id.bankbsi.dashboardumroh.dashboardumroh.model.response.WebResponse
import co.id.bankbsi.dashboardumroh.dashboardumroh.service.ApprovalService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
class ApprovalController(val service: ApprovalService) {

    @PostMapping(
        value = ["/api/approval"],
        produces = ["application/json"],
        consumes = ["application/json"]
    )
    fun createApproval(@RequestBody createApprovalRequest: CreateApprovalRequest): WebResponse<ApprovalResponse> {
        val approvalResponse = service.create(createApprovalRequest)
        return WebResponse(
            code = 200,
            status = "OK",
            data = approvalResponse
        )
    }

    @GetMapping(
        value = ["/api/approval"],
        produces = ["application/json"],
    )
    fun listApproval(
        @RequestParam(value = "size", defaultValue = "10") size: Int,
        @RequestParam(value = "page", defaultValue = "0") page: Int
    ): WebResponse<List<ApprovalResponse>> {
        val request = ListApprovalRequest(page, size)
        val response = service.listApproval(request)
        return WebResponse(
            code = 200,
            status = "OK",
            data = response
        )
    }
}