package co.id.bankbsi.dashboardumroh.dashboardumroh.model.request.umroh.transaksi


import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull


data class UmrohTransaksiRequest(
    @field:NotBlank
    val idTransaksi: String,

    @field:NotBlank
    val idMaster: String,

    @field:NotBlank
    val accDebit: String,

    @field:NotBlank
    val accCredit: String,

    val amount: Int,

    @field:NotBlank
    val paymentDetails: String,

    @field:NotBlank
    val ftRef: String,

    val status: Int,

    @field:NotBlank
    val createDate: String,

    @field:NotBlank
    val updateDate: String,

    @field:NotBlank
    val jenisBayar: String,

    @field:NotBlank
    val revFt: String,

    @field:NotBlank
    val revDate: String,

    val revStatus: Int,

    @field:NotBlank
    val pid: String,

    @field:NotBlank
    val pd1: String,

    @field:NotBlank
    val pd2: String,

    @field:NotBlank
    val pd3: String,

    @field:NotBlank
    val pd4: String,
)
