package co.id.bankbsi.dashboardumroh.dashboardumroh.service.impl

import co.id.bankbsi.dashboardumroh.dashboardumroh.entity.Auditrail
import co.id.bankbsi.dashboardumroh.dashboardumroh.model.request.auditrail.CreateAuditrailRequest
import co.id.bankbsi.dashboardumroh.dashboardumroh.model.request.auditrail.ListAuditrailRequest
import co.id.bankbsi.dashboardumroh.dashboardumroh.model.response.AuditrailResponse
import co.id.bankbsi.dashboardumroh.dashboardumroh.repository.AuditrailRepository
import co.id.bankbsi.dashboardumroh.dashboardumroh.service.AuditrailService
import co.id.bankbsi.dashboardumroh.dashboardumroh.validation.ValidationUtill
import org.springframework.data.domain.PageRequest
import org.springframework.stereotype.Service
import java.util.*
import java.util.stream.Collectors


@Service
class AuditrailServiceImpl(
    val auditrailRepository: AuditrailRepository,
    val validationUtill: ValidationUtill
) : AuditrailService {
    override fun create(auditrailRequest: CreateAuditrailRequest): AuditrailResponse {
        validationUtill.validate(auditrailRequest)
        val auditrail = Auditrail(
            auditrail = auditrailRequest.auditrail,
            createAt = Date(),
            typeData = auditrailRequest.typeData,
            dataAfter = auditrailRequest.dataAfter,
            dataBefore = auditrailRequest.dataBefore
        )
        auditrailRepository.save(auditrail)
        return convertAudiTrailToAuditrailResponse(auditrail)

    }

    override fun listAuditrail(listAuditrailRequest: ListAuditrailRequest): List<AuditrailResponse> {
        val page = auditrailRepository.findAll(PageRequest.of(listAuditrailRequest.page, listAuditrailRequest.size))
        val auditrail = page.get().collect(Collectors.toList())
        return auditrail.map { convertAudiTrailToAuditrailResponse(it) }
    }

    private fun convertAudiTrailToAuditrailResponse(auditrail: Auditrail): AuditrailResponse {
        return AuditrailResponse(
            auditrail = auditrail.auditrail,
            createAt = auditrail.createAt,
            typeData = auditrail.typeData,
            dataAfter = auditrail.dataAfter,
            dataBefore = auditrail.dataBefore
        )
    }

}