package co.id.bankbsi.dashboardumroh.dashboardumroh.service.umroh

import co.id.bankbsi.dashboardumroh.dashboardumroh.model.request.umroh.transaksi.TransaksiListRequest
import co.id.bankbsi.dashboardumroh.dashboardumroh.model.request.umroh.transaksi.UmrohTransaksiRequest
import co.id.bankbsi.dashboardumroh.dashboardumroh.model.request.umroh.transaksi.UmrohTransaksiUpdate
import co.id.bankbsi.dashboardumroh.dashboardumroh.model.response.umroh.UmrohTransaksiResponse

interface UmrohTransaksiService {
    fun create(umrohTransaksiRequest: UmrohTransaksiRequest):UmrohTransaksiResponse
    fun get(id:String) : UmrohTransaksiResponse
    fun list(listRequest: TransaksiListRequest): List<UmrohTransaksiResponse>
    fun update(id:String, umrohTransaksiUpdate: UmrohTransaksiUpdate): UmrohTransaksiResponse
    fun delete(id: String)
}