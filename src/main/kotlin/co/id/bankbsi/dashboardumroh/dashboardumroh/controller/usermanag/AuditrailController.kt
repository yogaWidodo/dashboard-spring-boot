package co.id.bankbsi.dashboardumroh.dashboardumroh.controller.usermanag

import co.id.bankbsi.dashboardumroh.dashboardumroh.model.request.auditrail.CreateAuditrailRequest
import co.id.bankbsi.dashboardumroh.dashboardumroh.model.request.auditrail.ListAuditrailRequest
import co.id.bankbsi.dashboardumroh.dashboardumroh.model.request.auditrail.UpdateAuditrailRequest
import co.id.bankbsi.dashboardumroh.dashboardumroh.model.response.AuditrailResponse
import co.id.bankbsi.dashboardumroh.dashboardumroh.model.response.WebResponse
import co.id.bankbsi.dashboardumroh.dashboardumroh.service.AuditrailService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/")
@CrossOrigin(originPatterns = ["*"])
class AuditrailController(val auditrailService: AuditrailService) {

    @PostMapping(
        value = ["auditrail"],
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
        value = ["auditrail/{id}"],
        produces = ["application/json"]
    )
    fun get(@PathVariable id: String): WebResponse<AuditrailResponse> {
        val auditrailResponse = auditrailService.get(id)
        return WebResponse(
            code = 200,
            status = "OK",
            data = auditrailResponse
        )
    }

    @DeleteMapping(
        value = ["auditrail/{id}"],
        produces = ["application/json"]
    )
    fun delete(@PathVariable id: String): WebResponse<AuditrailResponse> {
        val auditrailResponse = auditrailService.delete(id)
        return WebResponse(
            code = 200,
            status = "deleted",
            data = auditrailResponse
        )
    }

    @PutMapping(
        value = ["auditrail/{id}"],
        produces = ["application/json"],
        consumes = ["application/json"]
    )
    fun update(@PathVariable id: String, @RequestBody body: UpdateAuditrailRequest): WebResponse<AuditrailResponse> {
        val auditrailResponse = auditrailService.update(id, body)
        return WebResponse(
            code = 200,
            status = "OK",
            data = auditrailResponse
        )
    }

    @GetMapping(
        value = ["auditrail"],
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