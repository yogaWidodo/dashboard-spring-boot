package co.id.bankbsi.dashboardumroh.dashboardumroh.service.impl.umrohImpl

import co.id.bankbsi.dashboardumroh.dashboardumroh.model.entity.umroh.UmrohMsPayment
import co.id.bankbsi.dashboardumroh.dashboardumroh.model.request.umroh.paymentrequest.PaymentListRequest
import co.id.bankbsi.dashboardumroh.dashboardumroh.model.request.umroh.paymentrequest.UmrohMstPaymentRequest
import co.id.bankbsi.dashboardumroh.dashboardumroh.model.response.umroh.UmrohMsPaymentResponse
import co.id.bankbsi.dashboardumroh.dashboardumroh.repository.umroh.UmrohMsPaymentRepository
import co.id.bankbsi.dashboardumroh.dashboardumroh.service.umroh.UmrohMstPaymentService
import co.id.bankbsi.dashboardumroh.dashboardumroh.validation.ValidationUtill
import org.springframework.data.domain.PageRequest
import org.springframework.stereotype.Service
import java.util.stream.Collectors


@Service
class UmrohMsPaymentImpl(
    private val umrohMsPaymentRepository: UmrohMsPaymentRepository,
    private val validationUtill: ValidationUtill
) : UmrohMstPaymentService {
    override fun create(umrohMsPaymentRequest: UmrohMstPaymentRequest): UmrohMsPaymentResponse {
        validationUtill.validate(umrohMsPaymentRequest)
        val umrohMsPayment = UmrohMsPayment(
            idMaster = umrohMsPaymentRequest.idMaster,
            noCif = umrohMsPaymentRequest.noCif,
            noAcc = umrohMsPaymentRequest.noAcc,
            atasNamaRek = umrohMsPaymentRequest.atasNamaRek,
            noHp = umrohMsPaymentRequest.noHp,
            email = umrohMsPaymentRequest.email,
            idTravel = umrohMsPaymentRequest.idTravel,
            idPaket = umrohMsPaymentRequest.idPaket,
            namaPaket = umrohMsPaymentRequest.namaPaket,
            jenisPaket = umrohMsPaymentRequest.jenisPaket,
            hargaPaket = umrohMsPaymentRequest.hargaPaket,
            hargaHandling = umrohMsPaymentRequest.hargaHandling,
            minimalDp = umrohMsPaymentRequest.minimalDp,
            jumlahJamaah = umrohMsPaymentRequest.jumlahJamaah,
            tanggalKeberangkatan = umrohMsPaymentRequest.tanggalKeberangkatan,
            tanggalKepulangan = umrohMsPaymentRequest.tanggalKepulangan,
            caraBayar = umrohMsPaymentRequest.caraBayar,
            tipeKamar = umrohMsPaymentRequest.tipeKamar,
            catatan = umrohMsPaymentRequest.catatan,
            noPesanan = umrohMsPaymentRequest.noPesanan,
            kewajibanBayar = umrohMsPaymentRequest.kewajibanBayar,
            sisaBayar = umrohMsPaymentRequest.sisaBayar,
            tanggalPelunasan = umrohMsPaymentRequest.tanggalPelunasan,
            tanggalPembatalan = umrohMsPaymentRequest.tanggalPembatalan,
            statusBayar = umrohMsPaymentRequest.statusBayar,
            createDate = umrohMsPaymentRequest.createDate,
            updateDate = umrohMsPaymentRequest.updateDate,
            tanggalJatuhTempo = umrohMsPaymentRequest.tanggalJatuhTempo,
            statusSettlement = umrohMsPaymentRequest.statusSettlement,
            pid = umrohMsPaymentRequest.pid,
            amount1 = umrohMsPaymentRequest.amount1,
            amount2 = umrohMsPaymentRequest.amount2,
            paymentCode = umrohMsPaymentRequest.paymentCode,
            kotaKeberangkatan = umrohMsPaymentRequest.kotaKeberangkatan,
            statusFee = umrohMsPaymentRequest.statusFee,
            terminBayar = umrohMsPaymentRequest.terminBayar,
            kodeReferral = umrohMsPaymentRequest.kodeReferral
        )
        umrohMsPaymentRepository.save(umrohMsPayment)
        return umrohMsPayment.toResponse()
    }

    override fun get(id: String): UmrohMsPaymentResponse {
        return findMsPaymentByIdOrThrowNotFound(id).toResponse()
    }

    override fun list(listRequest: PaymentListRequest): List<UmrohMsPaymentResponse> {
        val page = umrohMsPaymentRepository.findAll(PageRequest.of(listRequest.page, listRequest.size))
        val msPayment = page.get().collect(Collectors.toList())
        return msPayment.map { it.toResponse() }
    }

//    override fun update(id: String, umrohMsPaymentUpdate: UmrohMsPaymentUpdate): UmrohMsPaymentResponse {
////        val msPayment = findMsPaymentByIdOrThrowNotFound(id)
////        validationUtill.validate(id)
////        msPayment.apply {
////
////        }
//        return
//    }

    override fun delete(id: String) {
        val msPayment = findMsPaymentByIdOrThrowNotFound(id)
        umrohMsPaymentRepository.delete(msPayment)
    }

    private fun findMsPaymentByIdOrThrowNotFound(id: String): UmrohMsPayment {
        return umrohMsPaymentRepository.findById(id)
            .orElseThrow { IllegalArgumentException("UmrohMsPayment not found with id $id") }
    }

    private fun UmrohMsPayment.toResponse() = UmrohMsPaymentResponse(
        idMaster = this.idMaster,
        noCif = this.noCif,
        noAcc = this.noAcc,
        atasNamaRek = this.atasNamaRek,
        noHp = this.noHp,
        email = this.email,
        idTravel = this.idTravel,
        idPaket = this.idPaket,
        namaPaket = this.namaPaket,
        jenisPaket = this.jenisPaket,
        hargaPaket = this.hargaPaket,
        hargaHandling = this.hargaHandling,
        minimalDp = this.minimalDp,
        jumlahJamaah = this.jumlahJamaah,
        tanggalKeberangkatan = this.tanggalKeberangkatan,
        tanggalKepulangan = this.tanggalKepulangan,
        caraBayar = this.caraBayar,
        tipeKamar = this.tipeKamar,
        catatan = this.catatan,
        noPesanan = this.noPesanan,
        kewajibanBayar = this.kewajibanBayar,
        sisaBayar = this.sisaBayar,
        tanggalPelunasan = this.tanggalPelunasan,
        tanggalPembatalan = this.tanggalPembatalan,
        statusBayar = this.statusBayar,
        createDate = this.createDate,
        updateDate = this.updateDate,
        tanggalJatuhTempo = this.tanggalJatuhTempo,
        statusSettlement = this.statusSettlement,
        pid = this.pid,
        amount1 = this.amount1,
        amount2 = this.amount2,
        paymentCode = this.paymentCode,
        kotaKeberangkatan = this.kotaKeberangkatan,
        statusFee = this.statusFee,
        terminBayar = this.terminBayar,
        kodeReferral = this.kodeReferral
    )
}