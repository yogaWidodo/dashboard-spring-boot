package co.id.bankbsi.dashboardumroh.dashboardumroh.model.request.umroh.paymentrequest

import java.util.*

data class UmrohMsPaymentUpdate(


    val noCif: String,

    val noAcc: String,

    val atasNamaRek: String,

    val noHp: String,

    val email: String,

    val idTravel: String,

    val idPaket: String,
    val namaPaket: String,

    val jenisPaket: String,

    val hargaPaket: Int,

    val hargaHandling: Int,

    val minimalDp: Int,

    val jumlahJamaah: Int,

    val tanggalKeberangkatan: String,

    val tanggalKepulangan: String,

    val caraBayar: String,

    val tipeKamar: String,

    val catatan: String,

    val noPesanan: String,

    val kewajibanBayar: Int,

    val sisaBayar: Int,

    val tanggalPelunasan: String,

    val tanggalPembatalan: String,

    val statusBayar: Int,

    val updateDate: Date,

    val tanggalJatuhTempo: String,

    val statusSettlement: String,

    val pid: String,

    val amount1: Int,

    val amount2: Int,

    val paymentCode: String,

    val kotaKeberangkatan: String,

    val statusFee: String,

    val terminBayar: String,

    val kodeReferral: String
)
