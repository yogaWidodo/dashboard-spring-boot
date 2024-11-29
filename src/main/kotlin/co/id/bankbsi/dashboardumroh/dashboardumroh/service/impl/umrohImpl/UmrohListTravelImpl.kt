package co.id.bankbsi.dashboardumroh.dashboardumroh.service.impl.umrohImpl

import co.id.bankbsi.dashboardumroh.dashboardumroh.model.entity.umroh.UmrohListTravel
import co.id.bankbsi.dashboardumroh.dashboardumroh.model.request.umroh.listtravel.ListTravelListRequest
import co.id.bankbsi.dashboardumroh.dashboardumroh.model.request.umroh.listtravel.UmrohListTravelRequest
import co.id.bankbsi.dashboardumroh.dashboardumroh.model.request.umroh.listtravel.UmrohListTravelUpdate
import co.id.bankbsi.dashboardumroh.dashboardumroh.model.response.umroh.UmrohListTravelResponse
import co.id.bankbsi.dashboardumroh.dashboardumroh.repository.umroh.UmrohListTravelRepository
import co.id.bankbsi.dashboardumroh.dashboardumroh.service.umroh.UmrohListTravelService
import co.id.bankbsi.dashboardumroh.dashboardumroh.validation.ValidationUtill
import org.springframework.data.domain.PageRequest
import org.springframework.stereotype.Service
import java.util.stream.Collectors

@Service
class UmrohListTravelImpl(
    private val umrohListTravelRepository: UmrohListTravelRepository,
    private val validationUtill: ValidationUtill
) : UmrohListTravelService {
    override fun create(umrohListTravelRequest: UmrohListTravelRequest): UmrohListTravelResponse {
        validationUtill.validate(umrohListTravelRequest)
        val travel = UmrohListTravel(
            idList = umrohListTravelRequest.idList,
            kdTravel = umrohListTravelRequest.kdTravel,
            namaTravel = umrohListTravelRequest.namaTravel,
            alamat = umrohListTravelRequest.alamat,
            kota = umrohListTravelRequest.kota,
            email = umrohListTravelRequest.email,
            website = umrohListTravelRequest.website,
            logoTravel = umrohListTravelRequest.logoTravel,
            background = umrohListTravelRequest.background,
            telp = umrohListTravelRequest.telp
        )
        umrohListTravelRepository.save(travel)
        return travel.toResponse()
    }

    override fun list(listRequest: ListTravelListRequest): List<UmrohListTravelResponse> {
        val page = umrohListTravelRepository.findAll(PageRequest.of(listRequest.page, listRequest.size))
        val travel = page.get().collect(Collectors.toList())
        return travel.map { it.toResponse() }

    }

    override fun get(id: String): UmrohListTravelResponse {
        val travel = findUmrohListTravelOrThrowNotFound(id)
        return travel.toResponse()

    }

    override fun update(id: String, umrohListTravelUpdate: UmrohListTravelUpdate): UmrohListTravelResponse {
        TODO("Not yet implemented")
    }

    override fun delete(id: String) {
        val travel = findUmrohListTravelOrThrowNotFound(id)
        umrohListTravelRepository.delete(travel)
    }


    private fun UmrohListTravel.toResponse(): UmrohListTravelResponse =
        UmrohListTravelResponse(
            idList = this.idList,
            kdTravel = this.kdTravel,
            namaTravel = this.namaTravel,
            alamat = this.alamat,
            kota = this.kota,
            email = this.email,
            website = this.website,
            logoTravel = this.logoTravel,
            background = this.background,
            telp = this.telp
        )

    private fun findUmrohListTravelOrThrowNotFound(id: String): UmrohListTravel {
        return umrohListTravelRepository.findById(id)
            .orElseThrow { IllegalArgumentException("UmrohListTravel not found with id: $id") }
    }


}