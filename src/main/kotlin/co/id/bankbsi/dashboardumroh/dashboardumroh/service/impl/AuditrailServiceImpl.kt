package co.id.bankbsi.dashboardumroh.dashboardumroh.service.impl

import co.id.bankbsi.dashboardumroh.dashboardumroh.error.NotFoundException
import co.id.bankbsi.dashboardumroh.dashboardumroh.model.entity.Auditrail
import co.id.bankbsi.dashboardumroh.dashboardumroh.model.request.auditrail.CreateAuditrailRequest
import co.id.bankbsi.dashboardumroh.dashboardumroh.model.request.auditrail.ListAuditrailRequest
import co.id.bankbsi.dashboardumroh.dashboardumroh.model.request.auditrail.UpdateAuditrailRequest
import co.id.bankbsi.dashboardumroh.dashboardumroh.model.response.AuditrailResponse
import co.id.bankbsi.dashboardumroh.dashboardumroh.repository.AuditrailRepository
import co.id.bankbsi.dashboardumroh.dashboardumroh.service.AuditrailService
import co.id.bankbsi.dashboardumroh.dashboardumroh.validation.ValidationUtill
import org.springframework.data.domain.PageRequest
import org.springframework.data.repository.findByIdOrNull
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
            auditrail = auditrailRequest.idAuditrail,
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

    override fun update(id: String, auditrailRequest: UpdateAuditrailRequest): AuditrailResponse {
        val auditrail = findAuditrailByIdOrThrowNotFound(id)
        validationUtill.validate(auditrailRequest)
        auditrail.apply {
            typeData = auditrailRequest.typeData
            dataAfter = auditrailRequest.dataAfter
            dataBefore = auditrailRequest.dataBefore
        }
        auditrailRepository.save(auditrail)
        return convertAudiTrailToAuditrailResponse(auditrail)
    }

    override fun delete(id: String): AuditrailResponse {
        val auditrail = findAuditrailByIdOrThrowNotFound(id)
        auditrailRepository.delete(auditrail)
        return convertAudiTrailToAuditrailResponse(auditrail)
    }

    override fun get(id: String): AuditrailResponse {
        val auditrail = findAuditrailByIdOrThrowNotFound(id)
        return convertAudiTrailToAuditrailResponse(auditrail)
    }

    private fun convertAudiTrailToAuditrailResponse(auditrail: Auditrail): AuditrailResponse {
        return AuditrailResponse(
            idAuditrail = auditrail.auditrail,
            createAt = auditrail.createAt,
            typeData = auditrail.typeData,
            dataAfter = auditrail.dataAfter,
            dataBefore = auditrail.dataBefore
        )
    }

    private fun findAuditrailByIdOrThrowNotFound(id: String): Auditrail {
        val auditrail = auditrailRepository.findByIdOrNull(id)
        if (auditrail == null) {
            throw NotFoundException()
        } else {
            return auditrail
        }

    }

}