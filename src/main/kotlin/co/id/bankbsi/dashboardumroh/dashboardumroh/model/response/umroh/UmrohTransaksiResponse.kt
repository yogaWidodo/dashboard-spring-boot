package co.id.bankbsi.dashboardumroh.dashboardumroh.model.response.umroh

import java.util.Date


data class UmrohTransaksiResponse(
    val idTransaksi: String,
    val idMaster: String,
    val accDebit: String,
    val accCredit: String,
    val amount: Int,
    val paymentDetails: String,
    val ftRef: String,
    val status: Int,
    val createDate: String,
    val createTime: Date,
    val updateDate: String,
    val updateTime: Date,
    val jenisBayar: String,
    val revFt: String,
    val revDate: String,
    val revTime: Date,
    val revStatus: Int,
    val pid: String,
    val pd1: String,
    val pd2: String,
    val pd3: String,
    val pd4: String,
)
