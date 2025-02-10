package co.id.bankbsi.dashboardumroh.dashboardumroh.controller.usermanag

import co.id.bankbsi.dashboardumroh.dashboardumroh.model.request.approval.CreateApprovalRequest
import co.id.bankbsi.dashboardumroh.dashboardumroh.model.request.approval.ListApprovalRequest
import co.id.bankbsi.dashboardumroh.dashboardumroh.model.request.approval.RemarkApproval
import co.id.bankbsi.dashboardumroh.dashboardumroh.model.request.approval.UpdateApprovalRequest
import co.id.bankbsi.dashboardumroh.dashboardumroh.model.response.usermanag.ApprovalResponse
import co.id.bankbsi.dashboardumroh.dashboardumroh.model.response.WebResponse
import co.id.bankbsi.dashboardumroh.dashboardumroh.service.usermanag.ApprovalService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/approval")
@CrossOrigin(originPatterns = ["*"])
//@PreAuthorize("hasRole('ADMIN') or hasRole('OFFICER') or hasRole('SPV') or hasRole('REPORTING')")
class ApprovalController(
    private val service: ApprovalService,
) {

    @PostMapping(
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
        value = ["/{id}"],
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
        value = ["/{id}"],
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

    @GetMapping
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

    @PostMapping("/setting/approve/{idApproval}/user/{userLdap}")
    fun approveSetting(@PathVariable idApproval: Int, @PathVariable userLdap: String): WebResponse<Boolean> {
        val response = service.approveChangeSettingParameter(idApproval, userLdap)
        return WebResponse(
            code = 200,
            status = "APPROVED",
            data = response
        )
    }

    @PostMapping("/setting/reject/{idApproval}/user/{userLdap}")
    fun rejectSetting(
        @PathVariable idApproval: Int,
        @PathVariable userLdap: String,
        @RequestBody remarkApproval: RemarkApproval
    ): WebResponse<Boolean> {
        val response = service.rejectChangeSettingParameter(idApproval, userLdap, remarkApproval)
        return WebResponse(
            code = 200,
            status = "REJECT",
            data = response
        )
    }

    @PostMapping("/role/approve/{idApproval}/user/{userLdap}")
    fun approveRole(@PathVariable idApproval: Int, @PathVariable userLdap: String): WebResponse<Boolean> {
        val response = service.approveRole(idApproval, userLdap)
        return WebResponse(
            code = 200,
            status = "APPROVED",
            data = response
        )
    }

    @PostMapping("/role/reject/{idApproval}/user/{userLdap}")
    fun rejectRole(
        @PathVariable idApproval: Int,
        @PathVariable userLdap: String,
        @RequestBody remarkApproval: RemarkApproval
    ): WebResponse<Boolean> {
        val response = service.rejectRole(idApproval, userLdap, remarkApproval)
        return WebResponse(
            code = 200,
            status = "REJECT",
            data = response
        )
    }

    @PostMapping("/travel/approve/{idApproval}/user/{userLdap}")
    fun approveTravel(@PathVariable idApproval: Int, @PathVariable userLdap: String): WebResponse<Boolean> {
        val response = service.approveChangeTravel(idApproval, userLdap)
        return WebResponse(
            code = 200,
            status = "APPROVED",
            data = response
        )
    }

    @PostMapping("/travel/reject/{idApproval}/user/{userLdap}")
    fun rejectTravel(
        @PathVariable idApproval: Int,
        @PathVariable userLdap: String,
        @RequestBody remarkApproval: RemarkApproval
    ): WebResponse<Boolean> {
        val response = service.rejectChangeTravel(idApproval, userLdap, remarkApproval)
        return WebResponse(
            code = 200,
            status = "REJECT",
            data = response
        )
    }

    @PostMapping("/user/approve/{idApproval}/marker/{userLdap}")
    fun approveUser(
        @PathVariable idApproval: Int,
        @PathVariable userLdap: String
    ): WebResponse<Boolean> {
        val response = service.approveUser(idApproval, userLdap)
        return WebResponse(
            code = 200,
            status = "APPROVED",
            data = response
        )
    }

    @PostMapping("/user/reject/{idApproval}/marker/{userLdap}")
    fun rejectUser(
        @PathVariable idApproval: Int,
        @PathVariable userLdap: String,
        @RequestBody remarkApproval: RemarkApproval
    ): WebResponse<Boolean> {
        val response = service.rejectUser(idApproval, userLdap, remarkApproval)
        return WebResponse(
            code = 200,
            status = "REJECT",
            data = response
        )
    }

}