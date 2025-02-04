package co.id.bankbsi.dashboardumroh.dashboardumroh.model.entity.umroh

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table
import java.util.Date

@Entity
@Table(name = "UMROH_MSTPAYMENT", schema = "UMROH123")
data class UmrohMstPayment(

    @Column(name = "ID_MASTER")
    @Id
    val idMaster: String,

    @Column(name = "NO_CIF")
    val noCif: String,

    @Column(name = "NO_ACC")
    val noAcc: String,

    @Column(name = "ATAS_NAMA_REK")
    val atasNamaRek: String,

    @Column(name = "NO_HP")
    val noHp: String,

    @Column(name = "EMAIL")
    val email: String,

    @Column(name = "ID_TRAVEL")
    val idTravel: String,

    @Column(name = "ID_PAKET")
    val idPaket: String,

    @Column(name = "NAMA_PAKET")
    val namaPaket: String,

    @Column(name = "JENIS_PAKET")
    val jenisPaket: String,

    @Column(name = "HARGA_PAKET")
    val hargaPaket: Int,

    @Column(name = "HARGA_HANDLING")
    val hargaHandling: Int,

    @Column(name = "MINIMAL_DP")
    val minimalDp: Int,

    @Column(name = "JML_JAMAAH")
    val jumlahJamaah: Int,

    @Column(name = "TGL_KEBERANGKATAN")
    val tanggalKeberangkatan: String,

    @Column(name = "TGL_KEPULANGAN")
    val tanggalKepulangan: String,

    @Column(name = "CARA_BAYAR")
    val caraBayar: String,

    @Column(name = "TIPE_KAMAR")
    val tipeKamar: String,

    @Column(name = "CATATAN")
    val catatan: String,

    @Column(name = "NO_PESANAN")
    val noPesanan: String,

    @Column(name = "KEWAJIBAN_BAYAR")
    val kewajibanBayar: Int,

    @Column(name = "SISA_BAYAR")
    val sisaBayar: Int,

    @Column(name = "TGL_PELUNASAN")
    val tanggalPelunasan: String,

    @Column(name = "TGL_PEMBATALAN")
    val tanggalPembatalan: String,

    @Column(name = "STATUS_BAYAR")
    val statusBayar: Int,

    @Column(name = "CREATE_DATE")
    val createDate: Date,

    @Column(name = "UPDATE_DATE")
    val updateDate: Date,

    @Column(name = "TGL_JATUH_TEMPO")
    val tanggalJatuhTempo: String,

    @Column(name = "STATUS_SETTLEMENT")
    val statusSettlement: String,

    @Column(name = "PID")
    val pid: String,

    @Column(name = "AMOUNT1")
    val amount1: Int,

    @Column(name = "AMOUNT2")
    val amount2: Int,

    @Column(name = "PAYMENT_CODE")
    val paymentCode: String,

    @Column(name = "KOTA_KEBERANGKATAN")
    val kotaKeberangkatan: String,

    @Column(name = "STATUS_FEE")
    val statusFee: String,

    @Column(name = "TERMIN_BAYAR")
    val terminBayar: String,

    @Column(name = "KODE_REFERRAL")
    val kodeReferral: String,

    @Column(name = "TRX_REFF_NO")
    val trxReffNo: String
)
