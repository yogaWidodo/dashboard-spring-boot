package co.id.bankbsi.dashboardumroh.dashboardumroh.controller.usermanag

import co.id.bankbsi.dashboardumroh.dashboardumroh.model.request.auditrail.CreateAuditrailRequest
import co.id.bankbsi.dashboardumroh.dashboardumroh.model.request.auditrail.ListAuditrailRequest
import co.id.bankbsi.dashboardumroh.dashboardumroh.model.request.auditrail.UpdateAuditrailRequest
import co.id.bankbsi.dashboardumroh.dashboardumroh.model.response.usermanag.AuditrailResponse
import co.id.bankbsi.dashboardumroh.dashboardumroh.model.response.WebResponse
import co.id.bankbsi.dashboardumroh.dashboardumroh.service.usermanag.AuditrailService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/auditrail")
@CrossOrigin(originPatterns = ["*"])
//@PreAuthorize("hasRole('ADMIN') or hasRole('OFFICER') or hasRole('SPV') or hasRole('REPORTING')")
class AuditrailController(val auditrailService: AuditrailService) {

    @PostMapping(
        produces = ["application/json"],
        consumes = ["application/json"]
    )
    fun createAuditrail(@RequestBody body: CreateAuditrailRequest): WebResponse<AuditrailResponse> {
        val auditrailResponse = auditrailService.create(body)
        return WebResponse(
            code = 200,
            status = "OK",
            data = auditrailResponse
        )
    }

    @GetMapping(
        value = ["/{id}"],
        produces = ["application/json"]
    )
    fun get(@PathVariable id: Int): WebResponse<AuditrailResponse> {
        val auditrailResponse = auditrailService.get(id)
        return WebResponse(
            code = 200,
            status = "OK",
            data = auditrailResponse
        )
    }



    @PutMapping(
        value = ["/{id}"],
        produces = ["application/json"],
        consumes = ["application/json"]
    )
    fun update(@PathVariable id: Int, @RequestBody body: UpdateAuditrailRequest): WebResponse<AuditrailResponse> {
        val auditrailResponse = auditrailService.update(id, body)
        return WebResponse(
            code = 200,
            status = "OK",
            data = auditrailResponse
        )
    }

    @GetMapping(
        produces = ["application/json"]
    )
    fun listAuditrail(
        @RequestParam(value = "size", defaultValue = "10") size: Int,
        @RequestParam(value = "page", defaultValue = "0") page: Int
    ): WebResponse<List<AuditrailResponse>> {
        val request = ListAuditrailRequest(page, size)
        val response = auditrailService.listAuditrail(request)
        return WebResponse(
            code = 200,
            status = "Succesfull",
            data = response
        )
    }
}