package co.id.bankbsi.dashboardumroh.dashboardumroh.service.impl.umrohImpl

import co.id.bankbsi.dashboardumroh.dashboardumroh.error.NotFoundException
import co.id.bankbsi.dashboardumroh.dashboardumroh.model.entity.umroh.UmrohTransaksi
import co.id.bankbsi.dashboardumroh.dashboardumroh.model.request.umroh.transaksi.TransaksiListRequest
import co.id.bankbsi.dashboardumroh.dashboardumroh.model.request.umroh.transaksi.UmrohTransaksiRequest
import co.id.bankbsi.dashboardumroh.dashboardumroh.model.request.umroh.transaksi.UmrohTransaksiUpdate
import co.id.bankbsi.dashboardumroh.dashboardumroh.model.response.umroh.UmrohTransaksiResponse
import co.id.bankbsi.dashboardumroh.dashboardumroh.repository.umroh.UmrohTransaksiRepository
import co.id.bankbsi.dashboardumroh.dashboardumroh.service.umroh.UmrohTransaksiService
import co.id.bankbsi.dashboardumroh.dashboardumroh.validation.ValidationUtill
import org.springframework.data.domain.PageRequest
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import java.util.*
import java.util.stream.Collectors


@Service
class UmrohTransaksiImpl(
    private val umrohTransaksiRepository: UmrohTransaksiRepository,
    private val validationUtill: ValidationUtill
) : UmrohTransaksiService {
    override fun create(umrohTransaksiRequest: UmrohTransaksiRequest): UmrohTransaksiResponse {
        validationUtill.validate(umrohTransaksiRequest)
        val transaksi = UmrohTransaksi(
            idTransaksi = umrohTransaksiRequest.idTransaksi,
            idMaster = umrohTransaksiRequest.idMaster,
            accDebit = umrohTransaksiRequest.accDebit,
            accCredit = umrohTransaksiRequest.accCredit,
            amount = umrohTransaksiRequest.amount,
            paymentDetails = umrohTransaksiRequest.paymentDetails,
            ftRef = umrohTransaksiRequest.ftRef,
            status = umrohTransaksiRequest.status,
            createDate = umrohTransaksiRequest.createDate,
            createTime = Date(),
            updateDate = umrohTransaksiRequest.updateDate,
            updateTime = Date(),
            jenisBayar = umrohTransaksiRequest.jenisBayar,
            revFt = umrohTransaksiRequest.revFt,
            revDate = umrohTransaksiRequest.revDate,
            revTime = Date(),
            revStatus = umrohTransaksiRequest.revStatus,
            pid = umrohTransaksiRequest.pid,
            pd1 = umrohTransaksiRequest.pd1,
            pd2 = umrohTransaksiRequest.pd2,
            pd3 = umrohTransaksiRequest.pd3,
            pd4 = umrohTransaksiRequest.pd4,
            reffno = umrohTransaksiRequest.reffNo
        )
        umrohTransaksiRepository.save(transaksi)
        return transaksi.toResponse()
    }

    override fun get(id: String): UmrohTransaksiResponse {
        val transaksi = findTransaksiByIdOrThrowNotFound(id)
        return transaksi.toResponse()
    }

    override fun list(listRequest: TransaksiListRequest): List<UmrohTransaksiResponse> {
        val page = umrohTransaksiRepository.findAll(PageRequest.of(listRequest.page, listRequest.size))
        val transaksi = page.get().collect(Collectors.toList())
        return transaksi.map { it.toResponse() }

    }

    override fun update(id: String, umrohTransaksiUpdate: UmrohTransaksiUpdate): UmrohTransaksiResponse {
        val transaksi = findTransaksiByIdOrThrowNotFound(id)
        validationUtill.validate(umrohTransaksiUpdate)
        transaksi.apply {
            status = umrohTransaksiUpdate.status
            updateTime = Date()
            revFt = umrohTransaksiUpdate.revFt
            revDate = umrohTransaksiUpdate.revDate
            revTime = umrohTransaksiUpdate.revTime
            pid = umrohTransaksiUpdate.pid
            pd1 = umrohTransaksiUpdate.pd1
            pd2 = umrohTransaksiUpdate.pd2
            pd3 = umrohTransaksiUpdate.pd3
            pd4 = umrohTransaksiUpdate.pd4
        }
        umrohTransaksiRepository.save(transaksi)
        return transaksi.toResponse()
    }

    override fun delete(id: String) {
        val transaksi = findTransaksiByIdOrThrowNotFound(id)
        umrohTransaksiRepository.delete(transaksi)
    }


    private fun UmrohTransaksi.toResponse() = UmrohTransaksiResponse(
        idTransaksi = this.idTransaksi,
        idMaster = this.idMaster,
        accDebit = this.accDebit,
        accCredit = this.accCredit,
        amount = this.amount,
        paymentDetails = this.paymentDetails,
        ftRef = this.ftRef,
        status = this.status,
        createDate = this.createDate,
        createTime = this.createTime,
        updateDate = this.updateDate,
        updateTime = this.updateTime,
        jenisBayar = this.jenisBayar,
        revFt = this.revFt,
        revDate = this.revDate,
        revTime = this.revTime,
        revStatus = this.revStatus,
        pid = this.pid,
        pd1 = this.pd1,
        pd2 = this.pd2,
        pd3 = this.pd3,
        pd4 = this.pd4,
        reffNo = this.reffno
    )

    private fun findTransaksiByIdOrThrowNotFound(id: String): UmrohTransaksi {
        val transaksi = umrohTransaksiRepository.findByIdOrNull(id)
        if (transaksi == null) {
            throw NotFoundException()
        } else {
            return transaksi;
        }
    }
}