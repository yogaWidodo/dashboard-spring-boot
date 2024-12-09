package co.id.bankbsi.dashboardumroh.dashboardumroh.controller.usermanag

import co.id.bankbsi.dashboardumroh.dashboardumroh.model.request.approval.CreateApprovalRequest
import co.id.bankbsi.dashboardumroh.dashboardumroh.model.request.approval.ListApprovalRequest
import co.id.bankbsi.dashboardumroh.dashboardumroh.model.request.approval.UpdateApprovalRequest
import co.id.bankbsi.dashboardumroh.dashboardumroh.model.response.ApprovalResponse
import co.id.bankbsi.dashboardumroh.dashboardumroh.model.response.WebResponse
import co.id.bankbsi.dashboardumroh.dashboardumroh.service.ApprovalService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/")
@CrossOrigin(originPatterns = ["*"])
class ApprovalController(val service: ApprovalService) {

    @PostMapping(
        value = ["approval"],
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
        value = ["approval/{id}"],
        produces = ["application/json"],
    )
    fun getApproval(@PathVariable id: Int): WebResponse<ApprovalResponse> {
        val approvalResponse = service.get(id)
        return WebResponse(
            code = 200,
            status = "OK",
            data = approvalResponse
        )
    }

    @PutMapping(
        value = ["approval/{id}"],
        produces = ["application/json"],
        consumes = ["application/json"]
    )
    fun update(
        @PathVariable id: Int,
        @RequestBody updateApprovalRequest: UpdateApprovalRequest
    ): WebResponse<ApprovalResponse> {
        val approval = service.update(id, updateApprovalRequest)
        return WebResponse(
            code = 200,
            status = "Update",
            data = approval
        )
    }

    @DeleteMapping(
        value = ["approval/{id}"],
        produces = ["application/json"],
    )
    fun delete(@PathVariable id: Int): WebResponse<String> {
        val approval = service.delete(id)
        return WebResponse(
            code = 200,
            status = "deleted",
            data = "data $approval has been deleted"
        )
    }

    @GetMapping(
        value = ["approval"],
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