package co.id.bankbsi.dashboardumroh.dashboardumroh.model.request.umroh.transaksi

import java.sql.Date


data class UmrohTransaksiUpdate(
    val status: Int,
    val revFt: String,
    val revDate: String,
    val revTime: Date,
    val rev: Int,
    val pid: String,
    val pd1: String,
    val pd2: String,
    val pd3: String,
    val pd4: String,
    )
