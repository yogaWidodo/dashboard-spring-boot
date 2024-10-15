package co.id.bankbsi.dashboardumroh.dashboardumroh.controller

import co.id.bankbsi.dashboardumroh.dashboardumroh.model.request.auditrail.CreateAuditrailRequest
import co.id.bankbsi.dashboardumroh.dashboardumroh.model.request.auditrail.ListAuditrailRequest
import co.id.bankbsi.dashboardumroh.dashboardumroh.model.response.AuditrailResponse
import co.id.bankbsi.dashboardumroh.dashboardumroh.model.response.WebResponse
import co.id.bankbsi.dashboardumroh.dashboardumroh.service.AuditrailService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
class AuditrailController(val auditrailService: AuditrailService) {

    @PostMapping(
        value = ["/api/auditrail"],
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
        value = ["/api/auditrail"],
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
            status="Succesfull",
            data = response
        )
    }
}