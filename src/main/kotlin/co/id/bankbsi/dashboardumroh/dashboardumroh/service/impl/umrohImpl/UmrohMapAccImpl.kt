package co.id.bankbsi.dashboardumroh.dashboardumroh.service.impl.umrohImpl

import co.id.bankbsi.dashboardumroh.dashboardumroh.model.entity.umroh.UmrohMapAcc
import co.id.bankbsi.dashboardumroh.dashboardumroh.model.request.umroh.mapacc.MapAccListRequest
import co.id.bankbsi.dashboardumroh.dashboardumroh.model.request.umroh.mapacc.UmrohMapAccRequest
import co.id.bankbsi.dashboardumroh.dashboardumroh.model.request.umroh.mapacc.UmrohMapAccUpdate
import co.id.bankbsi.dashboardumroh.dashboardumroh.model.response.umroh.UmrohMapAccResponse
import co.id.bankbsi.dashboardumroh.dashboardumroh.repository.umroh.UmrohMapAccRepository
import co.id.bankbsi.dashboardumroh.dashboardumroh.service.umroh.UmrohMapAccService
import co.id.bankbsi.dashboardumroh.dashboardumroh.validation.ValidationUtill
import org.springframework.data.domain.PageRequest
import org.springframework.stereotype.Service
import java.sql.Date
import java.util.stream.Collectors


@Service
class UmrohMapAccImpl(
    private val umrohMapAccRepository: UmrohMapAccRepository,
    private val validationUtill: ValidationUtill
) : UmrohMapAccService {
    override fun create(umrohMapAccRequest: UmrohMapAccRequest): UmrohMapAccResponse {
        validationUtill.validate(umrohMapAccRequest)
        val umrohMapAcc = UmrohMapAcc(
            idTravel = umrohMapAccRequest.idTravel,
            kdTravel = umrohMapAccRequest.kdTravel,
            noRekening = umrohMapAccRequest.noRekening,
            status = umrohMapAccRequest.status,
            createDate = umrohMapAccRequest.createDate,
            lastUpdate = umrohMapAccRequest.lastUpdate,
            createBy = umrohMapAccRequest.createBy,
            idMapAcc = umrohMapAccRequest.idMapAcc,
            namaTravel = umrohMapAccRequest.namaTravel,
            nominalFee = umrohMapAccRequest.nominalFee,
            alamat = umrohMapAccRequest.alamat,
            kota = umrohMapAccRequest.kota,
            email = umrohMapAccRequest.email,
            website = umrohMapAccRequest.website,
            logoTravel = umrohMapAccRequest.logoTravel,
            backround = umrohMapAccRequest.backround,
            telp = umrohMapAccRequest.telp
        )
        umrohMapAccRepository.save(umrohMapAcc)
        return umrohMapAcc.toResponse()
    }

    override fun get(id: Int): UmrohMapAccResponse {
        val umrohMapAcc = findUmrohMapAccByIdOrThrowNotFound(id)
        return umrohMapAcc.toResponse()
    }

    private fun findUmrohMapAccByIdOrThrowNotFound(id: Int): UmrohMapAcc {
        return umrohMapAccRepository.findById(id).orElseThrow{
            IllegalArgumentException("UmrohMapAcc not found with $id")
        }
    }

    override fun list(listRequest: MapAccListRequest): List<UmrohMapAccResponse> {
        val page = umrohMapAccRepository.findAll(PageRequest.of(listRequest.page, listRequest.size))
        val mapAcc = page.get().collect(Collectors.toList())
        return mapAcc.map { it.toResponse() }
    }

    override fun update(id: Int, umrohMapAccUpdate: UmrohMapAccUpdate): UmrohMapAccResponse {
        TODO("Not yet implemented")
    }

    override fun delete(id: Int) {
        val mapAcc = findUmrohMapAccByIdOrThrowNotFound(id)
        umrohMapAccRepository.delete(mapAcc)
    }

    private fun UmrohMapAcc.toResponse() = UmrohMapAccResponse(
        idTravel = this.idTravel,
        kdTravel = this.kdTravel,
        noRekening = this.noRekening,
        status = this.status,
        createDate = this.createDate,
        lastUpdate = this.lastUpdate,
        createBy = this.createBy,
        idMapAcc = this.idMapAcc,
        namaTravel = this.namaTravel,
        nominalFee = this.nominalFee,
        alamat = this.alamat,
        kota = this.kota,
        email = this.email,
        website = this.website,
        logoTravel = this.logoTravel,
        backround = this.backround,
        telp = this.telp
    )

}