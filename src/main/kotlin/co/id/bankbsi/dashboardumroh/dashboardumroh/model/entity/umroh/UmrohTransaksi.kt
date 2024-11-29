package co.id.bankbsi.dashboardumroh.dashboardumroh.model.entity.umroh

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table
import java.util.Date


@Entity
@Table(name = "UMROH_TRANSAKSI")
data class UmrohTransaksi(
    @Id
    @Column(name = "ID_TRANSAKSI")
    val idTransaksi: String,

    @Column(name = "ID_MASTER")
    val idMaster: String,

    @Column(name = "ACC_DEBIT")
    val accDebit: String,

    @Column(name = "ACC_CREDIT")
    val accCredit: String,

    @Column(name = "AMOUNT")
    val amount: Int,

    @Column(name = "PAYMENT_DETAILS")
    val paymentDetails: String,

    @Column(name = "FT_REF")
    val ftRef: String,

    @Column(name = "STATUS")
    var status: Int,

    @Column(name = "CREATE_DATE")
    val createDate: String,

    @Column(name = "CREATE_TIME")
    val createTime: Date,

    @Column(name = "UPDATE_DATE")
    var updateDate: String,

    @Column(name = "UPDATE_TIME")
    var updateTime: Date,

    @Column(name = "JENIS_BAYAR")
    val jenisBayar: String,

    @Column(name = "REV_FT")
    var revFt: String,

    @Column(name = "REV_DATE")
    var revDate: String,

    @Column(name = "REV_TIME")
    var revTime: Date,

    @Column(name = "REV_STATUS")
    val revStatus: Int,

    @Column(name = "PID")
    var pid: String,

    @Column(name = "PD1")
    var pd1: String,

    @Column(name = "PD2")
    var pd2: String,

    @Column(name = "PD3")
    var pd3: String,

    @Column(name = "PD4")
    var pd4: String,
)
