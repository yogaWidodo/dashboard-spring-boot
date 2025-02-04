package co.id.bankbsi.dashboardumroh.dashboardumroh.model.request.umroh.transaksi


import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull


data class UmrohTransaksiRequest(
    val idTransaksi: String,
    val idMaster: String,
    val accDebit: String,
    val accCredit: String,
    val amount: Int,
    val paymentDetails: String,
    val ftRef: String,
    val status: Int,
    val createDate: String,
    val updateDate: String,
    val jenisBayar: String,
    val revFt: String,
    val revDate: String,
    val revStatus: Int,
    val pid: String,
    val pd1: String,
    val pd2: String,
    val pd3: String,
    val pd4: String,
    val reffNo: String,
)
