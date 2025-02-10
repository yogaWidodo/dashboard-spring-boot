package co.id.bankbsi.dashboardumroh.dashboardumroh.service.impl.usermanag

import co.id.bankbsi.dashboardumroh.dashboardumroh.error.NotFoundException
import co.id.bankbsi.dashboardumroh.dashboardumroh.model.entity.usermanag.Auditrail
import co.id.bankbsi.dashboardumroh.dashboardumroh.model.entity.usermanag.mapToAuditrailResponse
import co.id.bankbsi.dashboardumroh.dashboardumroh.model.request.auditrail.CreateAuditrailRequest
import co.id.bankbsi.dashboardumroh.dashboardumroh.model.request.auditrail.ListAuditrailRequest
import co.id.bankbsi.dashboardumroh.dashboardumroh.model.request.auditrail.UpdateAuditrailRequest
import co.id.bankbsi.dashboardumroh.dashboardumroh.model.response.usermanag.AuditrailResponse
import co.id.bankbsi.dashboardumroh.dashboardumroh.repository.usermanag.AuditrailRepository
import co.id.bankbsi.dashboardumroh.dashboardumroh.service.usermanag.AuditrailService
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
            createAt = Date(),
            typeData = auditrailRequest.typeData,
            dataAfter = auditrailRequest.dataAfter,
            dataBefore = auditrailRequest.dataBefore
        )

        auditrailRepository.save(auditrail)
        return auditrail.mapToAuditrailResponse()

    }

    override fun listAuditrail(listAuditrailRequest: ListAuditrailRequest): List<AuditrailResponse> {
        val page = auditrailRepository.findAllAuditrailByCreateAtDesc(PageRequest.of(listAuditrailRequest.page, listAuditrailRequest.size))
        val auditrail = page.get().collect(Collectors.toList())
        return auditrail.map { it.mapToAuditrailResponse() }
    }

    override fun update(id: Int, auditrailRequest: UpdateAuditrailRequest): AuditrailResponse {
        val auditrail = findAuditrailByIdOrThrowNotFound(id)
        validationUtill.validate(auditrailRequest)
        auditrail.apply {
            typeData = auditrailRequest.typeData
            dataAfter = auditrailRequest.dataAfter
            dataBefore = auditrailRequest.dataBefore
        }
        auditrailRepository.save(auditrail)
        return auditrail.mapToAuditrailResponse()
    }

    override fun delete(id: Int): AuditrailResponse {
        val auditrail = findAuditrailByIdOrThrowNotFound(id)
        auditrailRepository.delete(auditrail)
        return auditrail.mapToAuditrailResponse()
    }

    override fun get(id: Int): AuditrailResponse {
        val auditrail = findAuditrailByIdOrThrowNotFound(id)
        return auditrail.mapToAuditrailResponse()
    }





    private fun findAuditrailByIdOrThrowNotFound(id: Int): Auditrail {
        val auditrail = auditrailRepository.findByIdOrNull(id)
        if (auditrail == null) {
            throw NotFoundException()
        } else {
            return auditrail
        }

    }

}