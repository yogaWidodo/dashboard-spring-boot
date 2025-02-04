package co.id.bankbsi.dashboardumroh.dashboardumroh.service.impl.umrohImpl

import co.id.bankbsi.dashboardumroh.dashboardumroh.model.entity.umroh.UmrohDetailJamaah
import co.id.bankbsi.dashboardumroh.dashboardumroh.model.request.umroh.detailjamaah.DetailJamaahListRequest
import co.id.bankbsi.dashboardumroh.dashboardumroh.model.request.umroh.detailjamaah.UmrohDetailJamaahRequest
import co.id.bankbsi.dashboardumroh.dashboardumroh.model.request.umroh.detailjamaah.UmrohDetailJamaahUpdate
import co.id.bankbsi.dashboardumroh.dashboardumroh.model.response.umroh.UmrohDetailJamaahResponse
import co.id.bankbsi.dashboardumroh.dashboardumroh.repository.umroh.UmrohDetailJamaahRepository
import co.id.bankbsi.dashboardumroh.dashboardumroh.service.umroh.UmrohDetailJamaahService
import co.id.bankbsi.dashboardumroh.dashboardumroh.validation.ValidationUtill
import org.springframework.data.domain.PageRequest
import org.springframework.stereotype.Service
import java.util.stream.Collectors

@Service
class UmrohDetailJamaahImpl(
    private val umrohDetailJamaahRepository: UmrohDetailJamaahRepository,
    private val validationUtill: ValidationUtill
) : UmrohDetailJamaahService {
    override fun create(umrohDetailJamaahRequest: UmrohDetailJamaahRequest): UmrohDetailJamaahResponse {
        validationUtill.validate(umrohDetailJamaahRequest)
        val umrohDetailJamaah = UmrohDetailJamaah(
            idTblJamaah = umrohDetailJamaahRequest.idTblJamaah,
            idMaster = umrohDetailJamaahRequest.idMaster,
            nik = umrohDetailJamaahRequest.nik,
            noHp = umrohDetailJamaahRequest.noHp,
            nama = umrohDetailJamaahRequest.nama,
            jkel = umrohDetailJamaahRequest.jkel,
            idJamaah = umrohDetailJamaahRequest.idJamaah,
            noRekHaji = umrohDetailJamaahRequest.noRekHaji
        )
        umrohDetailJamaahRepository.save(umrohDetailJamaah)
        return umrohDetailJamaah.toResponse()
    }

    override fun get(id: String): UmrohDetailJamaahResponse {
        val listTravel = findUmrohDetailJamaahOrThrowNotFound(id)
        return listTravel.toResponse()
    }

    override fun list(listRequest: DetailJamaahListRequest): List<UmrohDetailJamaahResponse> {
        val page = umrohDetailJamaahRepository.findAll(PageRequest.of(listRequest.page, listRequest.size))
        val listTravel = page.get().collect(Collectors.toList())
        return listTravel.map { it.toResponse() }
    }

    override fun update(id: String, umrohDetailJamaahUpdate: UmrohDetailJamaahUpdate): UmrohDetailJamaahResponse {
        val travel = findUmrohDetailJamaahOrThrowNotFound(id)
        travel.apply {
            idTblJamaah = umrohDetailJamaahUpdate.idTblJamaah
            idMaster = umrohDetailJamaahUpdate.idMaster
            nik = umrohDetailJamaahUpdate.nik
            noHp = umrohDetailJamaahUpdate.noHp
            nama = umrohDetailJamaahUpdate.nama
            jkel = umrohDetailJamaahUpdate.jkel
            idJamaah = umrohDetailJamaahUpdate.idJamaah
            noRekHaji = umrohDetailJamaahUpdate.noRekHaji
        }
        umrohDetailJamaahRepository.save(travel)
        return travel.toResponse()
    }

    override fun delete(id: String) {
        val travel = findUmrohDetailJamaahOrThrowNotFound(id)
        umrohDetailJamaahRepository.delete(travel)
    }

    private fun UmrohDetailJamaah.toResponse() =
        UmrohDetailJamaahResponse(
            idTblJamaah = idTblJamaah,
            idMaster = idMaster,
            nik = nik,
            noHp = noHp,
            nama = nama,
            jkel = jkel,
            idJamaah = idJamaah,
            noRekHaji = noRekHaji
        )

    private fun findUmrohDetailJamaahOrThrowNotFound(id: String): UmrohDetailJamaah {
        return umrohDetailJamaahRepository.findById(id)
            .orElseThrow { IllegalArgumentException("UmrohDetailJamaah not found with id: $id") }
    }
}